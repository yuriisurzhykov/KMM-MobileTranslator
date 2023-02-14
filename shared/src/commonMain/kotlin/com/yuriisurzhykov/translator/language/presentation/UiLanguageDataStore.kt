package com.yuriisurzhykov.translator.language.presentation

import com.yuriisurzhykov.translator.language.data.Language

interface UiLanguageDataStore {
    fun getAllLanguages(): List<UiLanguage>

    abstract class Abstract(
        private val mapper: UiLanguageMapper
    ) : UiLanguageDataStore {
        override fun getAllLanguages(): List<UiLanguage> {
            return Language.values().map { UiLanguage.byCode(it.code) }
        }
    }
}

expect fun UiLanguageDataStore(): UiLanguageDataStore.Abstract