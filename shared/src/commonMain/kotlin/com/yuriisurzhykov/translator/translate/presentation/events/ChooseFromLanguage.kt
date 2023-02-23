package com.yuriisurzhykov.translator.translate.presentation.events

import com.yuriisurzhykov.translator.language.presentation.UiLanguage
import com.yuriisurzhykov.translator.translate.presentation.TranslateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class ChooseFromLanguage(val language: UiLanguage) : TranslateEvent {
    override fun updateState(
        state: MutableStateFlow<TranslateState>,
        doBeforeUpdate: () -> Unit,
        doAfterUpdate: (TranslateState) -> Unit
    ) {
        state.update {
            val fromLanguage = language
            val toLanguage = if (it.toLanguage == language) it.fromLanguage else it.toLanguage
            it.copy(
                isChoosingFromLanguage = false,
                fromLanguage = fromLanguage,
                toLanguage = toLanguage
            )
        }
    }
}