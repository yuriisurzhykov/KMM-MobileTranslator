package com.yuriisurzhykov.translator.history.domain

import com.yuriisurzhykov.translator.core.Mapper
import com.yuriisurzhykov.translator.history.presentation.HistoryPresentationItem
import database.HistoryEntity

class HistoryEntityMapper : Mapper<HistoryEntity, HistoryPresentationItem> {
    override fun map(input: HistoryEntity): HistoryPresentationItem {
        return HistoryPresentationItem(
            input.id, input.fromLanguageCode, input.fromText, input.toLanguageCode, input.toText
        )
    }
}