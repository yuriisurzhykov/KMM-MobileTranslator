package com.yuriisurzhykov.translator.translate.deepl.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TranslateResponseList(
    @SerialName("translations") val translations: List<TranslateResponseEntity>
) {
    override fun toString(): String {
        return translations.firstOrNull()?.translatedText.orEmpty()
    }
}