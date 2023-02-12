package com.yuriisurzhykov.translator.history.domain

import com.yuriisurzhykov.translator.core.Mapper
import com.yuriisurzhykov.translator.history.presentation.HistoryPresentationItem
import database.HistoryEntity

interface HistoryEntityListMapper : Mapper<List<HistoryEntity>, List<HistoryPresentationItem>> {

    abstract class Abstract(private val entityMapper: HistoryEntityMapper) :
        HistoryEntityListMapper {
        override fun map(input: List<HistoryEntity>): List<HistoryPresentationItem> {
            return input.map { entityMapper.map(it) }
        }
    }

    class Base : Abstract(HistoryEntityMapper.Base())
}