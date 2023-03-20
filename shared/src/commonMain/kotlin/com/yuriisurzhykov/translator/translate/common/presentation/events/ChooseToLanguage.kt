package com.yuriisurzhykov.translator.translate.common.presentation.events

import com.yuriisurzhykov.translator.language.presentation.UiLanguage
import com.yuriisurzhykov.translator.translate.common.presentation.TranslateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet

data class ChooseToLanguage(val language: UiLanguage) : TranslateEvent {
    override fun updateState(
        state: MutableStateFlow<TranslateState>,
        doBeforeUpdate: () -> Unit,
        doAfterUpdate: (TranslateState) -> Unit
    ) {
        val newState =
            state.updateAndGet {
                val fromLanguage =
                    if (it.fromLanguage == language) it.toLanguage else it.fromLanguage
                val toLanguage = language
                it.copy(
                    isChoosingToLanguage = false,
                    fromLanguage = fromLanguage,
                    toLanguage = toLanguage
                )
            }
        doAfterUpdate.invoke(newState)
    }
}