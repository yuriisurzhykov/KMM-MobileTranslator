package com.yuriisurzhykov.translator.translate.presentation.events

import com.yuriisurzhykov.translator.translate.presentation.TranslateState
import kotlinx.coroutines.flow.MutableStateFlow

interface TranslateEvent {

    fun updateState(
        state: MutableStateFlow<TranslateState>,
        doBeforeUpdate: () -> Unit,
        doAfterUpdate: (TranslateState) -> Unit
    )

    object RecordAudio : TranslateEvent {
        override fun updateState(
            state: MutableStateFlow<TranslateState>,
            doBeforeUpdate: () -> Unit,
            doAfterUpdate: (TranslateState) -> Unit
        ) {
        }
    }

}