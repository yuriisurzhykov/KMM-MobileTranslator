package com.yuriisurzhykov.translator.language.data

enum class Language(
    val code: String,
    val countryCode: String,
    val langName: String
) {
    ENGLISH("en", "GB", "English"),
    ARABIC("ar", "AE", "عربي"),
    CHINESE("cn", "CN", "中国人"),
    CZECH("cs", "CZ", "Čeština"),
    FRENCH("fr", "FR", "Française"),
    GERMAN("de", "DE", "Deutsch"),
    HEBREW("he", "IL", "עִברִית"),
    ITALIAN("it", "IT", "Italiano"),
    IRISH("ga", "IE", "Gaeilge"),
    KOREAN("ko", "KR", "한국인"),
    POLISH("pl", "PL", "Polski"),
    PORTUGUESE("pt", "PT", "Português"),
    RUSSIAN("ru", "RU", "Русский"),
    UKRAINIAN("uk", "UA", "Українська")
}