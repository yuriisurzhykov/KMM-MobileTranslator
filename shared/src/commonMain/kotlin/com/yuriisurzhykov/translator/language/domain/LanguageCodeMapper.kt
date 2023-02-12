package com.yuriisurzhykov.translator.language.domain

import com.yuriisurzhykov.translator.core.Mapper
import com.yuriisurzhykov.translator.language.data.Language

class LanguageCodeMapper : Mapper.NotNullMapper<String, Language> {

    override fun map(input: String): Language {
        return when (input) {
            "en" -> Language.English
            "ar" -> Language.Arabic
            "cs" -> Language.Czech
            "he" -> Language.Hebrew
            "ga" -> Language.Irish
            "ko" -> Language.Korean
            "pl" -> Language.Polish
            "ru" -> Language.Russian
            "uk" -> Language.Ukrainian
            else -> Language.English
        }
    }
}