package com.yuriisurzhykov.translator.translate.data

/**
 * Interface to distinguish the result performed by [TranslateClient.translate]
 */
interface TranslateResult {

    fun getResult(): String

    class TranslationSuccess(private val result: TranslateResponseModel) : TranslateResult {
        override fun getResult() = result.translatedText
    }
}