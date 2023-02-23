package com.yuriisurzhykov.translator.voice.presentation

import com.yuriisurzhykov.translator.core.domain.CommonStateFlow
import com.yuriisurzhykov.translator.core.domain.asCommonStateFlow
import com.yuriisurzhykov.translator.voice.domain.VoiceToTextParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface VoiceToTextViewModel {

    fun state(): CommonStateFlow<VoiceToTextState>
    fun sendEvent(event: VoiceToTextEvent)

    abstract class Abstract(
        private val parser: VoiceToTextParser,
        coroutineScope: CoroutineScope? = null
    ) : VoiceToTextViewModel {
        private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
        private val mutableState = MutableStateFlow(VoiceToTextState())
        private val state = mutableState
            .combine(parser.state()) { state, voiceResult ->
                state.copy(
                    spokenText = voiceResult.result,
                    recordError = voiceResult.error,
                    displayState = when {
                        voiceResult.error != null -> DisplayState.Error()
                        voiceResult.result.isNotBlank() && !voiceResult.isSpeaking -> DisplayState.DisplayingResults()
                        voiceResult.isSpeaking -> DisplayState.Speaking()
                        else -> DisplayState.WaitingToTalk()
                    }
                )
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), VoiceToTextState())

        init {
            viewModelScope.launch {
                while (true) {
                    if (state.value.displayState is DisplayState.Speaking) {
                        mutableState.update {
                            it.copy(powerRatios = it.powerRatios + parser.state().value.powerRatio)
                        }
                    }
                    delay(50L)
                }
            }
        }

        override fun state() = state.asCommonStateFlow()

        override fun sendEvent(event: VoiceToTextEvent) {
            event.handleEvent(mutableState, parser)
        }
    }

    class Base(parser: VoiceToTextParser, coroutineScope: CoroutineScope) :
        Abstract(parser, coroutineScope)
}