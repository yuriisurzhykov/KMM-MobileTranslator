package com.yuriisurzhykov.translator.language.domain

import com.yuriisurzhykov.translator.core.Mapper
import com.yuriisurzhykov.translator.language.data.Language

class LanguageCodeMapper : Mapper.NotNullMapper<String, Language> {

    override fun map(input: String): Language {
        return Language.values().find { input == it.code } ?: Language.ENGLISH
    }
}