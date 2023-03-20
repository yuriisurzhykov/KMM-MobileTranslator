package com.yuriisurzhykov.translator.translate.common.data

import io.ktor.client.*

expect class HttpClientFactory {
    fun create(): HttpClient
}