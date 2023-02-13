package com.yuriisurzhykov.translator.language.presentation

import com.yuriisurzhykov.translator.core.Mapper

actual class CountryCodeEmojiMapper : Mapper<String, String> {
    override fun map(input: String): String {
        val firstLetter = Character.codePointAt(input, 0) - 0x41 + 0x1F1E6
        val secondLetter = Character.codePointAt(input, 1) - 0x41 + 0x1F1E6
        return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
    }
}