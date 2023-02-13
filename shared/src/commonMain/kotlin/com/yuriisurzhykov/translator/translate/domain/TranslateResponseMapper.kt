package com.yuriisurzhykov.translator.translate.domain

import com.yuriisurzhykov.translator.core.Mapper
import com.yuriisurzhykov.translator.history.domain.HistoryDomainItem
import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.translate.data.TranslateDataResult

interface TranslateResponseMapper : Mapper<TranslateDataResult, HistoryDomainItem> {

    fun map(
        fromText: String,
        fromLang: Language,
        toLanguage: Language,
        result: TranslateDataResult
    ): HistoryDomainItem

    class Base : TranslateResponseMapper {
        override fun map(input: TranslateDataResult) =
            throw RuntimeException("Not valid method for mapping $input!")

        override fun map(
            fromText: String,
            fromLang: Language,
            toLanguage: Language,
            result: TranslateDataResult
        ): HistoryDomainItem {
            return HistoryDomainItem(
                id = null,
                fromLanguageCode = fromLang.code,
                fromText = fromText,
                toLanguageCode = toLanguage.code,
                toText = result.getResult()
            )
        }
    }
}