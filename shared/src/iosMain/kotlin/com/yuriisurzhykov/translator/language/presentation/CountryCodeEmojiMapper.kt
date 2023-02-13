package com.yuriisurzhykov.translator.language.presentation

import com.yuriisurzhykov.translator.core.Mapper

actual class CountryCodeEmojiMapper : Mapper<String, String> {

    override fun map(input: String): String {
        val base = 127397
        val usv = StringBuilder()
        for (i in input.toCharArray()) {
            usv.append(base + i.code)
        }
        return usv.toString()
    }
}