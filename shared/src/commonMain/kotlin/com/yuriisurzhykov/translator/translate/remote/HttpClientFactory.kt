package com.yuriisurzhykov.translator.translate.remote

import io.ktor.client.*

expect class HttpClientFactory {
    fun create(): HttpClient
}