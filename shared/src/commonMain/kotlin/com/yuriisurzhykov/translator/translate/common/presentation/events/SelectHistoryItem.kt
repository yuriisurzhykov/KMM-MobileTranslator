package com.yuriisurzhykov.translator.translate.common.presentation.events

import com.yuriisurzhykov.translator.history.presentation.UiHistoryItem
import com.yuriisurzhykov.translator.translate.common.presentation.TranslateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class SelectHistoryItem(val item: UiHistoryItem) : TranslateEvent {
    override fun updateState(
        state: MutableStateFlow<TranslateState>,
        doBeforeUpdate: () -> Unit,
        doAfterUpdate: (TranslateState) -> Unit
    ) {
        doBeforeUpdate.invoke()
        state.update {
            it.copy(
                fromText = item.fromText,
                toText = item.toText,
                fromLanguage = item.fromLanguage,
                toLanguage = item.toLanguage,
                isTranslating = false
            )
        }
    }
}