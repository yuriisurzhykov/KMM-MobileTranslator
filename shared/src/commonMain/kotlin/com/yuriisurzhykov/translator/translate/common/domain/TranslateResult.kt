package com.yuriisurzhykov.translator.translate.common.domain

import com.yuriisurzhykov.translator.translate.common.data.TranslateDataResult
import com.yuriisurzhykov.translator.translate.common.data.TranslationError
import com.yuriisurzhykov.translator.translate.common.presentation.TranslateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface TranslateResult<T> {

    fun submitToState(state: MutableStateFlow<TranslateState>)

    class TranslationSuccess(private val result: TranslateDataResult) : TranslateResult<String> {
        override fun submitToState(state: MutableStateFlow<TranslateState>) {
            state.update { it.copy(toText = result.getResult(), isTranslating = false) }
        }
    }

    class TranslateError(private val translationError: TranslationError) :
        TranslateResult<TranslationError> {
        override fun submitToState(state: MutableStateFlow<TranslateState>) {
            state.update { it.copy(isTranslating = false, error = translationError) }
        }
    }
}