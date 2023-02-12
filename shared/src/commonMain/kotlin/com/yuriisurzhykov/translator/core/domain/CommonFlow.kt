package com.yuriisurzhykov.translator.core.domain

import kotlinx.coroutines.flow.Flow

/**
 * Transforms usual [Flow] instance to a [CommonFlow] that can work on both platforms: Android and iOS
 */
fun <T> Flow<T>.asCommonFlow() = CommonFlow(this)
expect class CommonFlow<T>(flow: Flow<T>) : Flow<T>