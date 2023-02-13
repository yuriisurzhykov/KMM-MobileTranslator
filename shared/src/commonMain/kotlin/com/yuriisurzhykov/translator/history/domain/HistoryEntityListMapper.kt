package com.yuriisurzhykov.translator.history.domain

import com.yuriisurzhykov.translator.core.Mapper
import database.HistoryEntity

interface HistoryEntityListMapper : Mapper<List<HistoryEntity>, List<HistoryDomainItem>> {

    abstract class Abstract(private val entityMapper: HistoryEntityMapper) :
        HistoryEntityListMapper {
        override fun map(input: List<HistoryEntity>): List<HistoryDomainItem> {
            return input.map { entityMapper.map(it) }
        }
    }

    class Base : Abstract(HistoryEntityMapper.Base())
}