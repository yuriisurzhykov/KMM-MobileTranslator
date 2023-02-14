package com.yuriisurzhykov.translator.language.presentation

import com.yuriisurzhykov.translator.core.Mapper

actual class CountryCodeEmojiMapper : Mapper<String, String> {
    override fun map(input: String): String {
        val code = input.uppercase()
        val firstLetter = Character.codePointAt(code, 0) - 0x41 + 0x1F1E6
        val secondLetter = Character.codePointAt(code, 1) - 0x41 + 0x1F1E6
        return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
    }
}