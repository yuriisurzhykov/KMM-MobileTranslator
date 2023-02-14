package com.yuriisurzhykov.translator.language.presentation

actual fun UiLanguageDataStore() = object : UiLanguageDataStore.Abstract(
    UiLanguageMapper(
        CountryCodeEmojiMapper()
    )
) {}