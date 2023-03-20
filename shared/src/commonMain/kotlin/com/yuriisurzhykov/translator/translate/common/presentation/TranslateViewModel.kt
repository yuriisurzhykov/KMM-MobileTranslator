package com.yuriisurzhykov.translator.translate.common.presentation

import com.yuriisurzhykov.translator.core.domain.asCommonStateFlow
import com.yuriisurzhykov.translator.history.domain.HistoryDataSource
import com.yuriisurzhykov.translator.history.presentation.UiHistoryItemMapper
import com.yuriisurzhykov.translator.translate.common.domain.TranslateUseCase
import com.yuriisurzhykov.translator.translate.common.presentation.events.TranslateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface TranslateViewModel {

    fun translate(state: TranslateState)
    fun translateStateFlow(): StateFlow<TranslateState>
    fun sendEvent(event: TranslateEvent)

    abstract class Abstract(
        private val translate: TranslateUseCase,
        private val historyItemMapper: UiHistoryItemMapper,
        historyDataSource: HistoryDataSource,
        coroutineScope: CoroutineScope?
    ) : TranslateViewModel {

        private var translateJob: Job? = null

        private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
        private val mutableState = MutableStateFlow(TranslateState())
        private val state =
            combine(mutableState, historyDataSource.getHistory()) { state, history ->
                if (state.history != history) {
                    state.copy(history = history.map { historyItemMapper.map(it) })
                } else state
            }.stateIn(
                viewModelScope, SharingStarted.WhileSubscribed(5000), TranslateState()
            ).asCommonStateFlow()

        override fun translateStateFlow(): StateFlow<TranslateState> = state

        override fun translate(state: TranslateState) {
            if (state.isTranslating || state.fromText.isBlank()) {
                return
            }
            translateJob = viewModelScope.launch {
                mutableState.update { it.copy(isTranslating = true) }
                val result = translate.translate(
                    text = state.fromText,
                    fromLanguage = state.fromLanguage.language,
                    toLanguage = state.toLanguage.language
                )
                result.submitToState(mutableState)
            }
        }

        override fun sendEvent(event: TranslateEvent) {
            event.updateState(
                state = mutableState,
                doBeforeUpdate = { translateJob?.cancel() },
                doAfterUpdate = { translate(it) }
            )
        }
    }

    class Base(
        translate: TranslateUseCase,
        historyItemMapper: UiHistoryItemMapper,
        historyDataSource: HistoryDataSource,
        coroutineScope: CoroutineScope?
    ) : Abstract(translate, historyItemMapper, historyDataSource, coroutineScope)
}