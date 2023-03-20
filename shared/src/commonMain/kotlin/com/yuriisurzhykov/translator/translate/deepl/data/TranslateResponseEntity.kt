package com.yuriisurzhykov.translator.translate.deepl.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TranslateResponseEntity(
    @SerialName("detected_source_language") val sourceLanguage: String,
    @SerialName("text") val translatedText: String
)