package com.yuriisurzhykov.translator.history.domain

import com.yuriisurzhykov.translator.core.data.Insert
import com.yuriisurzhykov.translator.core.domain.CommonFlow
import com.yuriisurzhykov.translator.history.presentation.HistoryPresentationItem

interface HistoryDataSource : Insert<HistoryPresentationItem> {

    fun getHistory(): CommonFlow<List<HistoryPresentationItem>>

}