package com.yuriisurzhykov.translator.voice.domain

interface VoiceToTextParser : VoiceParseStateProvider {
    fun startListening(languageCode: String)
    fun stopListening()
    fun cancel()
    fun reset()
}