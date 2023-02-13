package com.yuriisurzhykov.translator.history.data

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.yuriisurzhykov.translator.core.domain.CommonFlow
import com.yuriisurzhykov.translator.core.domain.asCommonFlow
import com.yuriisurzhykov.translator.database.TranslateDatabase
import com.yuriisurzhykov.translator.history.domain.HistoryDataSource
import com.yuriisurzhykov.translator.history.domain.HistoryEntityListMapper
import com.yuriisurzhykov.translator.history.domain.HistoryDomainItem
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(
    private val cacheToPresentationMapper: HistoryEntityListMapper,
    db: TranslateDatabase
) : HistoryDataSource {

    private val queries = db.translateQueries

    override suspend fun insert(item: HistoryDomainItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }

    override fun getHistory(): CommonFlow<List<HistoryDomainItem>> {
        return queries
            .getHistory()
            .asFlow()
            .mapToList()
            .map { list ->
                cacheToPresentationMapper.map(list)
            }
            .asCommonFlow()
    }
}
