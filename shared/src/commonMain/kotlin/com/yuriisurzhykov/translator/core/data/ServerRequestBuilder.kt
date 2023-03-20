package com.yuriisurzhykov.translator.core.data

import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.translate.common.data.TranslateRequestModel
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
        protected val pathBuilder: RequestPathBuilder,
        protected val requestUrl: String,
        private val keyProvider: ApiKeyProvider
    ) : ServerRequestBuilder<T> {

        protected abstract fun buildBody(text: String, fromLang: String, toLang: String): Any

        protected open fun buildUrl(text: String, fromLang: String, toLang: String): String {
            return pathBuilder.setUrl(requestUrl).build()
        }

        override fun transform(
            builder: HttpRequestBuilder,
            text: String,
            fromLang: Language,
            toLang: Language
        ): HttpRequestBuilder {
            builder.url(buildUrl(text, fromLang.code, toLang.code))
            builder.header(keyProvider.provideKeyHeaderName(), keyProvider.provideApiKey())
            builder.setBody(buildBody(text, fromLang.code, toLang.code))
            builder.contentType(ContentType.Application.Json)
            return builder
        }
    }

    class Translation(
        keyProvider: ApiKeyProvider,
        pathBuilder: RequestPathBuilder = RequestPathBuilder.Base()
    ) : Abstract<TranslateRequestModel>(
        pathBuilder = pathBuilder,
        keyProvider = keyProvider,
        requestUrl = "https://translation-api.translate.com/translate/v1/mt"
    ) {
        override fun buildBody(text: String, fromLang: String, toLang: String): Any {
            return Json.encodeToString(
                TranslateRequestModel.serializer(),
                TranslateRequestModel(text, fromLang, toLang)
            )
        }
    }

    class DeepL(
        keyProvider: ApiKeyProvider,
        pathBuilder: RequestPathBuilder = RequestPathBuilder.Base()
    ) : Abstract<com.yuriisurzhykov.translator.translate.deepl.data.TranslateRequestModel>(
        keyProvider = keyProvider,
        pathBuilder = pathBuilder,
        requestUrl = "https://api.deepl.com/v2/translate"
    ) {

        override fun buildUrl(text: String, fromLang: String, toLang: String): String {
            return pathBuilder.setUrl(requestUrl)
                .addPath("text", text)
                .addPath("source_lang", fromLang)
                .addPath("target_lang", toLang)
                .build()
        }

        override fun buildBody(text: String, fromLang: String, toLang: String): Any {
            return Any()
        }
    }
}