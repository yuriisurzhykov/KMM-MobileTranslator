package com.yuriisurzhykov.translator.history.domain

data class HistoryDomainItem(
    val id: Long?,
    val fromLanguageCode: String,
    val fromText: String,
    val toLanguageCode: String,
    val toText: String
)