package com.yuriisurzhykov.translator.translate.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TranslateRequestModel(
    @SerialName("q") val textToTranslate: String,
    @SerialName("source") val sourceLanguageCode: String,
    @SerialName("target") val targetLanguageCode: String
)