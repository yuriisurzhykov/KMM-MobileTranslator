package com.yuriisurzhykov.translator.translate.common.data

/**
 * Interface to distinguish the result performed by [TranslateClient.translate]
 */
interface TranslateDataResult {

    fun getResult(): String

    class TranslationSuccess(private val result: String) : TranslateDataResult {
        override fun getResult() = result
    }
}