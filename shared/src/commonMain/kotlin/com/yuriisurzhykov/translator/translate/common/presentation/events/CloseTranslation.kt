package com.yuriisurzhykov.translator.translate.common.presentation.events

import com.yuriisurzhykov.translator.translate.common.presentation.TranslateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object CloseTranslation : TranslateEvent {
    override fun updateState(
        state: MutableStateFlow<TranslateState>,
        doBeforeUpdate: () -> Unit,
        doAfterUpdate: (TranslateState) -> Unit
    ) {
        state.update { it.copy(isTranslating = false, fromText = "", toText = "") }
    }
}