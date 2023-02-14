package com.yuriisurzhykov.translator.language.data

enum class Language(
    val code: String,
    val countryCode: String,
    val langName: String
) {
    ENGLISH("en", "GB", "English"),
    ARABIC("ar", "AE", "عربي"),
    CZECH("cs", "CZ", "Čeština"),
    GERMAN("de", "DE", "Deutsch"),
    HEBREW("he", "IL", "עִברִית"),
    IRISH("ga", "IE", "Gaeilge"),
    KOREAN("ko", "KR", "한국인"),
    POLISH("pl", "PL", "Polski"),
    RUSSIAN("ru", "RU", "Русский"),
    UKRAINIAN("uk", "UA", "Українська")
}