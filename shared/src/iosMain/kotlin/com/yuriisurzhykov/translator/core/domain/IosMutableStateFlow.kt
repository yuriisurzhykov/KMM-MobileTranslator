package com.yuriisurzhykov.translator.core.domain

import kotlinx.coroutines.flow.MutableStateFlow

class IosMutableStateFlow<T>(initialValue: T) :
    CommonMutableStateFlow<T>(MutableStateFlow(initialValue))