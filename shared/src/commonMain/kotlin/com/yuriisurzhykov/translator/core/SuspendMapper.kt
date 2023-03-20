package com.yuriisurzhykov.translator.core

interface SuspendMapper<I : Any, O : Any> {

    suspend fun map(input: I): O

    interface ListMapper<I, O> : SuspendMapper<List<I>, List<O>>
}