package com.yuriisurzhykov.translator.translate.common.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TranslateRequestModel(
    @SerialName("text") val textToTranslate: String,
    @SerialName("source_language") val sourceLanguageCode: String,
    @SerialName("translation_language") val targetLanguageCode: String
)