package com.yuriisurzhykov.translator.voice.presentation

import com.yuriisurzhykov.translator.voice.domain.VoiceToTextParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface VoiceToTextEvent {

    fun handleEvent(state: MutableStateFlow<VoiceToTextState>, parser: VoiceToTextParser)

    class Close : VoiceToTextEvent {
        override fun handleEvent(
            state: MutableStateFlow<VoiceToTextState>,
            parser: VoiceToTextParser
        ) {
        }
    }

    data class PermissionResult(
        val isGranted: Boolean,
        val isPermanentlyDeclined: Boolean
    ) : VoiceToTextEvent {
        override fun handleEvent(
            state: MutableStateFlow<VoiceToTextState>,
            parser: VoiceToTextParser
        ) {
            state.update { it.copy(canRecord = isGranted) }
        }

    }

    data class ToggleRecording(val languageCode: String) : VoiceToTextEvent {
        override fun handleEvent(
            state: MutableStateFlow<VoiceToTextState>,
            parser: VoiceToTextParser
        ) {
            parser.cancel()
            if (state.value.displayState is DisplayState.Speaking) {
                parser.stopListening()
            } else {
                parser.startListening(languageCode)
            }
        }
    }

    class Reset : VoiceToTextEvent {
        override fun handleEvent(
            state: MutableStateFlow<VoiceToTextState>,
            parser: VoiceToTextParser
        ) {
            parser.reset()
            state.update { VoiceToTextState() }
        }
    }
}