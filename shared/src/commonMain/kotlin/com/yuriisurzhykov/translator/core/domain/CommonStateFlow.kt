package com.yuriisurzhykov.translator.core.domain

import kotlinx.coroutines.flow.StateFlow

fun <T> StateFlow<T>.asCommonStateFlow() = CommonStateFlow(this)
expect class CommonStateFlow<T>(flow: StateFlow<T>) : StateFlow<T>