package com.yuriisurzhykov.translator.translate.data.remote

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TranslateResponseModel(
    @SerialName("translation") val translatedText: String,
    @SerialName("limit_used") val limitedUsers: Int,
    @SerialName("limit_remaining") val limitRemaining: Int
)