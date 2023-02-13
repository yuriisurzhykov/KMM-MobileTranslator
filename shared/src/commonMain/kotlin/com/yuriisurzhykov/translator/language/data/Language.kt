package com.yuriisurzhykov.translator.language.data

enum class Language(
    val code: String,
    val langName: String
) {
    ENGLISH("en", "English"),
    ARABIC("ar", "عربي"),
    CZECH("cs", "Čeština"),
    GERMAN("de", "Deutsch"),
    HEBREW("he", "עִברִית"),
    IRISH("ga", "Gaeilge"),
    KOREAN("ko", "한국인"),
    POLISH("pl", "Polski"),
    RUSSIAN("ru", "Русский"),
    UKRAINIAN("uk", "Українська")
}