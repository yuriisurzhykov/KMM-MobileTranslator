package com.yuriisurzhykov.translator.language.presentation

import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.language.domain.LanguageCodeMapper
import java.util.*

actual data class UiLanguage(
    actual val language: Language, actual val emojiLanguageCode: String
) {
    fun toLocale(): Locale? {
        return when (language) {
            Language.ENGLISH -> Locale.ENGLISH
            Language.GERMAN -> Locale.GERMAN
            Language.KOREAN -> Locale.KOREAN
            Language.CHINESE -> Locale.CHINESE
            else -> null
        }
    }

    actual companion object {
        actual fun byCode(code: String): UiLanguage {
            return UiLanguageMapper(CountryCodeEmojiMapper()).map(LanguageCodeMapper().map(code))
        }
    }

}