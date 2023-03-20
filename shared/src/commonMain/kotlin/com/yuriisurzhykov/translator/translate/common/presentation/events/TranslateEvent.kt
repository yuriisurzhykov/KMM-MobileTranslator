package com.yuriisurzhykov.translator.translate.common.presentation.events

import com.yuriisurzhykov.translator.translate.common.presentation.TranslateState
import kotlinx.coroutines.flow.MutableStateFlow

interface TranslateEvent {

    fun updateState(
        state: MutableStateFlow<TranslateState>,
        doBeforeUpdate: () -> Unit,
        doAfterUpdate: (TranslateState) -> Unit
    )

    object Translate : TranslateEvent {
        override fun updateState(
            state: MutableStateFlow<TranslateState>,
            doBeforeUpdate: () -> Unit,
            doAfterUpdate: (TranslateState) -> Unit
        ) {
            doAfterUpdate.invoke(state.value)
        }
    }

    object RecordAudio : TranslateEvent {
        override fun updateState(
            state: MutableStateFlow<TranslateState>,
            doBeforeUpdate: () -> Unit,
            doAfterUpdate: (TranslateState) -> Unit
        ) {
        }
    }

}