package com.yuriisurzhykov.translator.translate.deepl.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TranslateRequestModel(
    @SerialName("text") val textToTranslate: String,
    @SerialName("source_lang") val sourceLanguageCode: String,
    @SerialName("target_lang") val targetLanguageCode: String
)