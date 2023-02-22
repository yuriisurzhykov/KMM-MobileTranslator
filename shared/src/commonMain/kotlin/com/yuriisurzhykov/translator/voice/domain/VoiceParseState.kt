package com.yuriisurzhykov.translator.voice.domain

data class VoiceParseState(
    val result: String = "",
    val error: String? = null,
    val powerRatio: Float = 0f,
    val isSpeaking: Boolean = false
)