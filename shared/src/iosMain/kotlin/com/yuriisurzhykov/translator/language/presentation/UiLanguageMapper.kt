package com.yuriisurzhykov.translator.language.presentation

import com.yuriisurzhykov.translator.core.Mapper
import com.yuriisurzhykov.translator.language.data.Language

class UiLanguageMapper(
    private val countryCodeEmojiMapper: CountryCodeEmojiMapper
) : Mapper<Language, UiLanguage> {

    override fun map(input: Language): UiLanguage {
        return UiLanguage(input, countryCodeEmojiMapper.map(input.code))
    }
}