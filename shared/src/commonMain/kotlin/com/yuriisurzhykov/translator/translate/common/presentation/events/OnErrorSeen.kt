package com.yuriisurzhykov.translator.translate.common.presentation.events

import com.yuriisurzhykov.translator.translate.common.presentation.TranslateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object OnErrorSeen : TranslateEvent {
    override fun updateState(
        state: MutableStateFlow<TranslateState>,
        doBeforeUpdate: () -> Unit,
        doAfterUpdate: (TranslateState) -> Unit
    ) {
        state.update { it.copy(error = null) }
    }
}