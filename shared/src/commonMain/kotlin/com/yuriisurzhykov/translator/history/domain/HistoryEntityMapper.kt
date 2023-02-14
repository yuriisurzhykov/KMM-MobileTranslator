package com.yuriisurzhykov.translator.history.domain

import com.yuriisurzhykov.translator.core.Mapper
import database.HistoryEntity

interface HistoryEntityMapper : Mapper<HistoryEntity, HistoryDomainItem> {

    class Base : HistoryEntityMapper {
        override fun map(input: HistoryEntity): HistoryDomainItem {
            return HistoryDomainItem(
                input.id, input.fromLanguageCode, input.fromText, input.toLanguageCode, input.toText
            )
        }
    }
}