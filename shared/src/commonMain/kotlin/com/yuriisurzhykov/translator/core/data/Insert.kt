package com.yuriisurzhykov.translator.core.data

interface Insert<T> {

    suspend fun insert(item: T)
}