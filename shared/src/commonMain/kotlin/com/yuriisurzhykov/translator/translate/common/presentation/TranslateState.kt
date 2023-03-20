package com.yuriisurzhykov.translator.translate.common.presentation

import com.yuriisurzhykov.translator.history.presentation.UiHistoryItem
import com.yuriisurzhykov.translator.language.presentation.UiLanguage
import com.yuriisurzhykov.translator.translate.common.data.TranslationError

data class TranslateState(
    val fromText: String = "",
    val toText: String = "",
    val isTranslating: Boolean = false,
    val fromLanguage: UiLanguage = UiLanguage.byCode("en"),
    val toLanguage: UiLanguage = UiLanguage.byCode("ru"),
    val isChoosingFromLanguage: Boolean = false,
    val isChoosingToLanguage: Boolean = false,
    val error: TranslationError? = null,
    val history: List<UiHistoryItem> = emptyList()
)
