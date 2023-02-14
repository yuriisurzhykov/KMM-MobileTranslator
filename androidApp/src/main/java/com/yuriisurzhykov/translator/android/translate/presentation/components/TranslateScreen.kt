package com.yuriisurzhykov.translator.android.translate.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuriisurzhykov.translator.android.core.presentation.Routes
import com.yuriisurzhykov.translator.android.translate.presentation.AndroidTranslateViewModel
import com.yuriisurzhykov.translator.translate.presentation.TranslateState
import com.yuriisurzhykov.translator.translate.presentation.events.*

@Composable
fun TranslateScreen(
    state: TranslateState, onEvent: (TranslateEvent) -> Unit
) {
    Scaffold(floatingActionButton = {}) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
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
        }
    }
}

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