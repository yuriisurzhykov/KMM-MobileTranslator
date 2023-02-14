package com.yuriisurzhykov.translator.language.presentation

import com.yuriisurzhykov.translator.language.data.Language

expect class UiLanguage {
    val language: Language
    val emojiLanguageCode: String

    companion object {
        fun byCode(code: String): UiLanguage
    }
}