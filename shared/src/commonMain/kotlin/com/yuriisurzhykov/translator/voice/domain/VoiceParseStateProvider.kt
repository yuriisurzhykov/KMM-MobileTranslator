package com.yuriisurzhykov.translator.voice.domain

import com.yuriisurzhykov.translator.core.domain.CommonStateFlow

interface VoiceParseStateProvider {
    fun state(): CommonStateFlow<VoiceParseState>
}