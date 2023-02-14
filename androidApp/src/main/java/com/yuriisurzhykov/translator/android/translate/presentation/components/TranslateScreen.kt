@file:OptIn(ExperimentalComposeUiApi::class)

package com.yuriisurzhykov.translator.android.translate.presentation.components

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuriisurzhykov.translator.android.R
import com.yuriisurzhykov.translator.android.core.presentation.Routes
import com.yuriisurzhykov.translator.android.core.theme.defaultPadding
import com.yuriisurzhykov.translator.android.translate.presentation.AndroidTranslateViewModel
import com.yuriisurzhykov.translator.translate.data.TranslationError
import com.yuriisurzhykov.translator.translate.presentation.TranslateState
import com.yuriisurzhykov.translator.translate.presentation.events.*

@Composable
fun TranslateRoot() {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Routes.TRANSLATE.routeName
    ) {
        composable(route = Routes.TRANSLATE.routeName) {
            val viewModel = hiltViewModel<AndroidTranslateViewModel>()
            val state by viewModel.translateStateFlow().collectAsState()
            TranslateScreen(
                state = state,
                onEvent = viewModel::sendEvent
            )
        }
    }
}

@Composable
fun TranslateScreen(
    state: TranslateState, onEvent: (TranslateEvent) -> Unit
) {
    TranslateScreenErrorScope(state, onEvent)
    Scaffold(floatingActionButton = {}) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(defaultPadding),
            verticalArrangement = Arrangement.spacedBy(defaultPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TranslateScreenHeader(state, onEvent)
            }
            item {
                TranslateScreenInputs(state, onEvent)
            }
            item {
                TranslateScreenHistoryHeader(state)
            }
            items(state.history) { historyItem ->
                TranslateHistoryItem(
                    item = historyItem,
                    onClick = { onEvent(SelectHistoryItem(historyItem)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun TranslateScreenHeader(
    state: TranslateState,
    onEvent: (TranslateEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LanguageDropDown(
            language = state.fromLanguage,
            isOpen = state.isChoosingFromLanguage,
            onClick = { onEvent(OpenFromLanguageDropDown) },
            onDismiss = { onEvent(StopChoosingLanguage) },
            onSelectLanguage = { onEvent(ChooseFromLanguage(it)) }
        )
        Spacer(modifier = Modifier.weight(1f))
        SwapLanguagesButton(onClick = { onEvent(SwapLanguages) })
        Spacer(modifier = Modifier.weight(1f))
        LanguageDropDown(
            language = state.toLanguage,
            isOpen = state.isChoosingToLanguage,
            onClick = { onEvent(OpenToLanguageDropDown) },
            onDismiss = { onEvent(StopChoosingLanguage) },
            onSelectLanguage = { onEvent(ChooseToLanguage(it)) }
        )
    }
}


@Composable
fun TranslateScreenInputs(
    state: TranslateState,
    onEvent: (TranslateEvent) -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val tts = rememberTextToSpeech()
    TranslateTextField(
        fromText = state.fromText,
        toText = state.toText,
        isTranslating = state.isTranslating,
        fromLanguage = state.fromLanguage,
        toLanguage = state.toLanguage,
        onTranslateClick = {
            keyboardController?.hide()
            onEvent(TranslateEvent.Translate)
        },
        onTextChange = {
            onEvent(ChangeTranslationText(it))
        },
        onCopyClick = { text ->
            clipboardManager.setText(buildAnnotatedString { append(text) })
            Toast.makeText(
                context,
                context.getString(R.string.message_copied_to_clipboard),
                Toast.LENGTH_LONG
            ).show()
        },
        onCloseClick = { onEvent(CloseTranslation) },
        onSpeakerClick = {
            tts.language = state.toLanguage.toLocale()
            tts.speak(
                state.toText, TextToSpeech.QUEUE_FLUSH, null, null
            )
        },
        onTextFieldClick = {
            onEvent(EditTranslation)
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TranslateScreenHistoryHeader(
    state: TranslateState
) {
    if (state.history.isNotEmpty()) {
        Text(
            text = stringResource(R.string.label_history),
            style = MaterialTheme.typography.h2
        )
    }
}

@Composable
fun TranslateScreenErrorScope(
    state: TranslateState,
    onEvent: (TranslateEvent) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.error) {
        val message = when (state.error) {
            is TranslationError.ServiceNotAvailable -> context.getString(R.string.error_message_service_unavailable)
            is TranslationError.ServerError -> context.getString(R.string.error_message_server_error)
            is TranslationError.ClientError -> context.getString(R.string.error_message_client_error)
            is TranslationError.UnknownError -> context.getString(R.string.error_message_unknown_error)
            else -> null
        }
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            onEvent(OnErrorSeen)
        }
    }
}