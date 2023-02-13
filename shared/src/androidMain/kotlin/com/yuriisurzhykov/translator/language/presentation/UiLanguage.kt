package com.yuriisurzhykov.translator.language.presentation

import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.language.domain.LanguageCodeMapper

actual data class UiLanguage(
    actual val language: Language, actual val emojiLanguageCode: String
) {
    actual companion object {
        actual fun byCode(code: String): UiLanguage {
            return UiLanguageMapper(CountryCodeEmojiMapper()).map(LanguageCodeMapper().map(code))
        }
    }

}