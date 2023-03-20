@file:OptIn(ExperimentalAnimationApi::class)

package com.yuriisurzhykov.translator.android.voice.presentation

import android.Manifest
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yuriisurzhykov.translator.android.R
import com.yuriisurzhykov.translator.android.core.theme.AccentLight
import com.yuriisurzhykov.translator.android.voice.presentation.components.VoiceRecorderDisplay
import com.yuriisurzhykov.translator.voice.presentation.DisplayState
import com.yuriisurzhykov.translator.voice.presentation.VoiceToTextEvent
import com.yuriisurzhykov.translator.voice.presentation.VoiceToTextState

@Composable
fun VoiceToTextScreen(
    state: VoiceToTextState,
    languageCode: String,
    onResult: (String) -> Unit,
    onEvent: (VoiceToTextEvent) -> Unit
) {
    val context = LocalContext.current
    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            val componentActivity = (context as ComponentActivity)
            val permanentlyDeclined =
                !isGranted && !componentActivity.shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)
            onEvent(VoiceToTextEvent.PermissionResult(isGranted, permanentlyDeclined))
        }
    )
    LaunchedEffect(recordAudioLauncher) {
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(
                    onClick = {
                        if (state.displayState !is DisplayState.DisplayingResults) {
                            onEvent(VoiceToTextEvent.ToggleRecording(languageCode))
                        } else {
                            onResult(state.spokenText)
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.size(75.dp)
                ) {
                    AnimatedContent(targetState = state.displayState) { displayState ->
                        when (displayState) {
                            is DisplayState.DisplayingResults -> {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = stringResource(id = R.string.label_apply_recorded_result),
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            is DisplayState.Speaking -> {
                                Icon(
                                    imageVector = Icons.Rounded.Stop,
                                    contentDescription = stringResource(id = R.string.label_stop_recording),
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            else -> {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_microphone),
                                    contentDescription = stringResource(id = R.string.label_record_audio),
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                    }
                }
                if (state.displayState is DisplayState.DisplayingResults) {
                    IconButton(onClick = {
                        onEvent(VoiceToTextEvent.ToggleRecording(languageCode))
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Refresh,
                            contentDescription = stringResource(id = R.string.label_record_again),
                            tint = AccentLight
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = {
                        onEvent(VoiceToTextEvent.Close())
                    },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = stringResource(R.string.label_close)
                    )
                }
                if (state.displayState is DisplayState.Speaking) {
                    Text(
                        text = stringResource(R.string.label_listening),
                        color = AccentLight,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(bottom = 100.dp)
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedContent(targetState = state.displayState) { displayState ->
                    when (displayState) {
                        is DisplayState.WaitingToTalk -> {
                            Text(
                                text = stringResource(R.string.label_start_talking),
                                style = MaterialTheme.typography.h2,
                                textAlign = TextAlign.Center
                            )
                        }
                        is DisplayState.Speaking -> {
                            Log.e("TAG", "VoiceToTextScreen: rendering voice recorder display")
                            VoiceRecorderDisplay(
                                powerRatios = state.powerRatios,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                            )
                        }
                        is DisplayState.DisplayingResults -> {
                            Text(
                                text = state.spokenText,
                                style = MaterialTheme.typography.h2,
                                textAlign = TextAlign.Center
                            )
                        }
                        is DisplayState.Error -> {
                            Text(
                                text = state.recordError
                                    ?: stringResource(R.string.error_message_unknown_error),
                                style = MaterialTheme.typography.h2,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.error
                            )
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}