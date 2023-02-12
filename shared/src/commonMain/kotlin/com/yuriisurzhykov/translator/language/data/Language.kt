package com.yuriisurzhykov.translator.language.data

sealed class Language(
    val code: String,
    val name: String
) {
    object English : Language("en", "English")
    object Arabic : Language("ar", "عربي")
    object Czech : Language("cs", "Čeština")
    object Hebrew : Language("he", "עִברִית")
    object Irish : Language("ga", "Gaeilge")
    object Korean : Language("ko", "한국인")
    object Polish : Language("pl", "Polski")
    object Russian : Language("ru", "Русский")
    object Ukrainian : Language("uk", "Українська")
}