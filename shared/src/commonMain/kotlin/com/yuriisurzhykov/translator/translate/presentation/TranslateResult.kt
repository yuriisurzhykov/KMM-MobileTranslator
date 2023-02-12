package com.yuriisurzhykov.translator.translate.presentation

import com.yuriisurzhykov.translator.translate.data.TranslateDataResult
import com.yuriisurzhykov.translator.translate.data.TranslateResponseModel
import com.yuriisurzhykov.translator.translate.data.TranslationError

interface TranslateResult<T> {

    fun getResult(): T

    class TranslationSuccess(private val result: TranslateDataResult) : TranslateResult<String> {
        override fun getResult() = result.getResult()
    }

    class TranslateError(private val translationError: TranslationError) :
        TranslateResult<TranslationError> {

        override fun getResult() = translationError
    }
}