package com.yuriisurzhykov.translator.android.voice.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriisurzhykov.translator.core.domain.CommonStateFlow
import com.yuriisurzhykov.translator.voice.domain.VoiceToTextParser
import com.yuriisurzhykov.translator.voice.presentation.VoiceToTextEvent
import com.yuriisurzhykov.translator.voice.presentation.VoiceToTextState
import com.yuriisurzhykov.translator.voice.presentation.VoiceToTextViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidVoiceToTextViewModel @Inject constructor(
    private val parser: VoiceToTextParser
) : ViewModel(), VoiceToTextViewModel {

    private val sharedViewModel by lazy {
        VoiceToTextViewModel.Base(parser, viewModelScope)
    }

    override fun state(): CommonStateFlow<VoiceToTextState> = sharedViewModel.state()

    override fun sendEvent(event: VoiceToTextEvent) {
        sharedViewModel.sendEvent(event)
    }

}