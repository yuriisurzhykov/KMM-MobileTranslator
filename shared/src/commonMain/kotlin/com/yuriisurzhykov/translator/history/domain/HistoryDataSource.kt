package com.yuriisurzhykov.translator.history.domain

import com.yuriisurzhykov.translator.core.data.Insert
import com.yuriisurzhykov.translator.core.domain.CommonFlow

interface HistoryDataSource : Insert<HistoryDomainItem> {

    fun getHistory(): CommonFlow<List<HistoryDomainItem>>

}