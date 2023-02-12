package com.yuriisurzhykov.translator.core.domain

import com.yuriisurzhykov.translator.core.Mapper

inline fun <I, O> I.map(transform: (I) -> O): O {
    return transform.invoke(this)
}

inline fun <I, O> I.map(mapper: Mapper<I, O>): O {
    return mapper.map(this)
}