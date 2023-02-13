package com.yuriisurzhykov.translator.history.presentation

import com.yuriisurzhykov.translator.language.presentation.UiLanguage

data class UiHistoryItem(
    val id: Long,
    val fromText: String,
    val toText: String,
    val fromLanguage: UiLanguage,
    val toLanguage: UiLanguage
)