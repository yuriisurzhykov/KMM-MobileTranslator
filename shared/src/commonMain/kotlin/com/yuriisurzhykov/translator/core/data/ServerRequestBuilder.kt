package com.yuriisurzhykov.translator.core.data

import io.ktor.client.request.*
import io.ktor.http.*

interface ServerRequestBuilder<T> {

    fun transform(builder: HttpRequestBuilder): HttpRequestBuilder

    abstract class Abstract<T> : ServerRequestBuilder<T> {
        override fun transform(builder: HttpRequestBuilder): HttpRequestBuilder {
            builder.url()
            builder.contentType(ContentType.Application.Json)
            return builder
        }
    }

}