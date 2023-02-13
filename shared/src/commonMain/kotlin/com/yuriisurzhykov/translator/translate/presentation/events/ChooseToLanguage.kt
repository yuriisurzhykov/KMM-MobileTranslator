package com.yuriisurzhykov.translator.translate.presentation.events

import com.yuriisurzhykov.translator.language.presentation.UiLanguage
import com.yuriisurzhykov.translator.translate.presentation.TranslateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet

data class ChooseToLanguage(val language: UiLanguage) : TranslateEvent {
    override fun updateState(
        state: MutableStateFlow<TranslateState>,
        doBeforeUpdate: () -> Unit,
        doAfterUpdate: (TranslateState) -> Unit
    ) {
        val newState =
            state.updateAndGet { it.copy(isChoosingToLanguage = false, toLanguage = language) }
        doAfterUpdate.invoke(newState)
    }
}