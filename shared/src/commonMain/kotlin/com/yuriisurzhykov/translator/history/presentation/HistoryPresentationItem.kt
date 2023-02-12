package com.yuriisurzhykov.translator.history.presentation

data class HistoryPresentationItem(
    val id: Long?,
    val fromLanguageCode: String,
    val fromText: String,
    val toLanguageCode: String,
    val toText: String
)