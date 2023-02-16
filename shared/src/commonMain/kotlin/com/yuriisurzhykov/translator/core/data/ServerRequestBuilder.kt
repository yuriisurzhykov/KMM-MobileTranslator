package com.yuriisurzhykov.translator.core.data

import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.translate.data.remote.TranslateRequestModel
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

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
            val url = pathBuilder.setUrl("https://translation-api.translate.com/translate/v1/mt")
                .build()
            builder.url(url)
            builder.header("x-api-key", keyProvider.provideApiKey())
            builder.setBody(
                Json.encodeToString(
                    TranslateRequestModel.serializer(),
                    TranslateRequestModel(text, fromLang.code, toLang.code)
                )
            )
            builder.contentType(ContentType.Application.Json)
            return builder
        }
    }

    class Base(
        keyProvider: ApiKeyProvider,
        pathBuilder: RequestPathBuilder = RequestPathBuilder.Base()
    ) : Abstract<TranslateRequestModel>(pathBuilder, keyProvider)

}