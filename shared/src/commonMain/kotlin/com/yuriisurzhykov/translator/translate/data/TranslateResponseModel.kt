package com.yuriisurzhykov.translator.translate.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TranslateResponseModel(
    @SerialName("translatedText") val translatedText: String
)