package com.yuriisurzhykov.translator.core.domain

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<T>.asCommonMutableStateFlow() = CommonMutableStateFlow(this)
expect class CommonMutableStateFlow<T>(flow: MutableStateFlow<T>) : MutableStateFlow<T>