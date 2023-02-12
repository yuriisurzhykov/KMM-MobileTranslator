package com.yuriisurzhykov.translator.core

interface Mapper<I, O> {

    fun map(input: I): O

    interface NotNullMapper<I : Any, O : Any> : Mapper<I, O>

    interface EmptyMapper<I : Any> : NotNullMapper<I, Nothing>

}