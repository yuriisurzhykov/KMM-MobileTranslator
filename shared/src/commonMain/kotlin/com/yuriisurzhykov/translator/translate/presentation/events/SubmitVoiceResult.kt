package com.yuriisurzhykov.translator.translate.presentation.events

import com.yuriisurzhykov.translator.translate.presentation.TranslateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class SubmitVoiceResult(val result: String?) : TranslateEvent {
    override fun updateState(
        state: MutableStateFlow<TranslateState>,
        doBeforeUpdate: () -> Unit,
        doAfterUpdate: (TranslateState) -> Unit
    ) {
        state.update {
            it.copy(
                fromText = result ?: it.fromText,
                isTranslating = result != null,
                toText = if (result != null) "" else result.orEmpty()
            )
        }
    }
}