package com.yuriisurzhykov.translator.translate.deepl.data

data class TranslateRequestModel(
    val textToTranslate: String,
    val sourceLanguageCode: String,
    val targetLanguageCode: String
)