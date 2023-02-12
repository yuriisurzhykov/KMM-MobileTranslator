package com.yuriisurzhykov.translator.history.data

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.yuriisurzhykov.translator.core.domain.CommonFlow
import com.yuriisurzhykov.translator.core.domain.asCommonFlow
import com.yuriisurzhykov.translator.database.TranslateDatabase
import com.yuriisurzhykov.translator.history.domain.HistoryDataSource
import com.yuriisurzhykov.translator.history.domain.HistoryEntityMapper
import com.yuriisurzhykov.translator.history.presentation.HistoryPresentationItem
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(
    db: TranslateDatabase
) : HistoryDataSource {

    private val queries = db.translateQueries

    override suspend fun insert(item: HistoryPresentationItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }

    override fun getHistory(): CommonFlow<List<HistoryPresentationItem>> {
        return queries
            .getHistory()
            .asFlow()
            .mapToList()
            .map { list ->
                list.map { HistoryEntityMapper().map(it) }
            }
            .asCommonFlow()
    }
}
