package com.yuriisurzhykov.translator.translate.presentation

import com.yuriisurzhykov.translator.core.Mapper
import com.yuriisurzhykov.translator.history.presentation.HistoryPresentationItem
import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.translate.data.TranslateDataResult

interface TranslateResponseMapper : Mapper<TranslateDataResult, HistoryPresentationItem> {

    fun map(
        fromText: String,
        fromLang: Language,
        toLanguage: Language,
        result: TranslateDataResult
    ): HistoryPresentationItem

    class Base : TranslateResponseMapper {
        override fun map(input: TranslateDataResult) =
            throw RuntimeException("Not valid method for mapping $input!")

        override fun map(
            fromText: String,
            fromLang: Language,
            toLanguage: Language,
            result: TranslateDataResult
        ): HistoryPresentationItem {
            return HistoryPresentationItem(
                id = null,
                fromLanguageCode = fromLang.code,
                fromText = fromText,
                toLanguageCode = toLanguage.code,
                toText = result.getResult()
            )
        }
    }
}