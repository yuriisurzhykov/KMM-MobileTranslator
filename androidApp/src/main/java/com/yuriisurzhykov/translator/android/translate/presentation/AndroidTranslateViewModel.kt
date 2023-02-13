package com.yuriisurzhykov.translator.android.translate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriisurzhykov.translator.history.domain.HistoryDataSource
import com.yuriisurzhykov.translator.history.presentation.UiHistoryItemMapper
import com.yuriisurzhykov.translator.translate.domain.TranslateUseCase
import com.yuriisurzhykov.translator.translate.presentation.TranslateState
import com.yuriisurzhykov.translator.translate.presentation.TranslateViewModel
import com.yuriisurzhykov.translator.translate.presentation.events.TranslateEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AndroidTranslateViewModel @Inject constructor(
    translate: TranslateUseCase,
    historyItemMapper: UiHistoryItemMapper,
    historyDataSource: HistoryDataSource
) : ViewModel(), TranslateViewModel {

    private val sharedViewModel: TranslateViewModel by lazy {
        TranslateViewModel.Base(translate, historyItemMapper, historyDataSource, viewModelScope)
    }

    override fun translate(state: TranslateState) {
        sharedViewModel.translate(state)
    }

    override fun translateStateFlow(): StateFlow<TranslateState> {
        return sharedViewModel.translateStateFlow()
    }

    override fun sendEvent(event: TranslateEvent) {
        sharedViewModel.sendEvent(event)
    }
}