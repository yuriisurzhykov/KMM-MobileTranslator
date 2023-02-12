package com.yuriisurzhykov.translator.translate.data

/**
 * Interface to distinguish the result performed by [TranslateClient.translate]
 */
interface TranslateResult {

    class TranslationSuccess(private val result: TranslateResponseModel) : TranslateResult {
        fun getTranslatedText() = result.translatedText
    }
}