package com.yuriisurzhykov.translator.core.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

actual open class CommonFlow<T> actual constructor(
    private val flow: Flow<T>
) : Flow<T> by flow {

    fun subscribe(
        coroutineScope: CoroutineScope,
        coroutineDispatcher: CoroutineDispatcher,
        onCollect: (T) -> Unit
    ): DisposableHandle {
        val job = coroutineScope.launch(coroutineDispatcher) {
            flow.collect(onCollect)
        }
        return DisposableHandle { job.cancel() }
    }
}