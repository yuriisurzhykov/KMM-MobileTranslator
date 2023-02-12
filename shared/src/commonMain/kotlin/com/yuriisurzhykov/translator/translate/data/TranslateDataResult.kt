package com.yuriisurzhykov.translator.translate.data

import com.yuriisurzhykov.translator.translate.presentation.TranslateResult

/**
 * Interface to distinguish the result performed by [TranslateClient.translate]
 */
interface TranslateDataResult {

    fun getResult(): String

    class TranslationSuccess(private val result: TranslateResponseModel) : TranslateDataResult {
        override fun getResult() = result.translatedText
    }
}