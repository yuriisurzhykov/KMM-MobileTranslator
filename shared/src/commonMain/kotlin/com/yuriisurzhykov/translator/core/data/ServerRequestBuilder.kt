package com.yuriisurzhykov.translator.core.data

import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.translate.data.TranslateRequestModel
import io.ktor.client.request.*
import io.ktor.http.*

interface ServerRequestBuilder<T> {

    fun transform(
        builder: HttpRequestBuilder,
        text: String,
        fromLang: Language,
        toLang: Language
    ): HttpRequestBuilder

    abstract class Abstract<T> : ServerRequestBuilder<T> {
        override fun transform(
            builder: HttpRequestBuilder,
            text: String,
            fromLang: Language,
            toLang: Language
        ): HttpRequestBuilder {
            builder.url()
            builder.contentType(ContentType.Application.Json)
            builder.setBody(TranslateRequestModel(text, fromLang.code, toLang.code))
            return builder
        }
    }

    class Base : ServerRequestBuilder.Abstract<TranslateRequestModel>()

}