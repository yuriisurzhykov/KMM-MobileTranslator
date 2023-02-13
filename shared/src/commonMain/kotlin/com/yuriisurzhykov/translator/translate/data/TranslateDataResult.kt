package com.yuriisurzhykov.translator.translate.data

/**
 * Interface to distinguish the result performed by [TranslateClient.translate]
 */
interface TranslateDataResult {

    fun getResult(): String

    class TranslationSuccess(private val result: TranslateResponseModel) : TranslateDataResult {
        override fun getResult() = result.translatedText
    }
}