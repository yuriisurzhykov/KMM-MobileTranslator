package com.yuriisurzhykov.translator.history.presentation

import com.yuriisurzhykov.translator.core.Mapper
import com.yuriisurzhykov.translator.history.domain.HistoryDomainItem
import com.yuriisurzhykov.translator.language.presentation.UiLanguage

interface UiHistoryItemMapper : Mapper<HistoryDomainItem, UiHistoryItem> {

    abstract class Abstract(private val codeMapper: (String) -> UiLanguage) : UiHistoryItemMapper {
        override fun map(input: HistoryDomainItem): UiHistoryItem {
            if (input.id == null) throw IllegalStateException("Item $input must have id, but it has no!")
            return UiHistoryItem(
                id = input.id,
                fromText = input.fromText,
                fromLanguage = UiLanguage.byCode(input.fromLanguageCode),
                toText = input.toText,
                toLanguage = UiLanguage.byCode(input.toLanguageCode)
            )
        }
    }

    class Base : Abstract({ s -> UiLanguage.byCode(s) })
}