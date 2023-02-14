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

    abstract class Abstract<T>(
        private val pathBuilder: RequestPathBuilder,
        private val keyProvider: ApiKeyProvider
    ) :
        ServerRequestBuilder<T> {
        override fun transform(
            builder: HttpRequestBuilder,
            text: String,
            fromLang: Language,
            toLang: Language
        ): HttpRequestBuilder {
            val url = pathBuilder.setUrl("https://libretranslate.com/translate")
                .addPath("q", text)
                .addPath("source", fromLang.code)
                .addPath("target", toLang.code)
                .addPath("api_key", keyProvider.provideApiKey())
                .build()
            builder.url(url)
            builder.contentType(ContentType.Application.Json)
            builder.setBody(TranslateRequestModel(text, fromLang.code, toLang.code))
            return builder
        }
    }

    class Base(
        keyProvider: ApiKeyProvider,
        pathBuilder: RequestPathBuilder = RequestPathBuilder.Base()
    ) : Abstract<TranslateRequestModel>(pathBuilder, keyProvider)

}