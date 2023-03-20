package com.yuriisurzhykov.translator.core.data

import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.translate.common.data.TranslateRequestModel
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

interface ServerRequestBuilder {

    fun transform(
        builder: HttpRequestBuilder,
        text: String,
        fromLang: Language,
        toLang: Language
    ): HttpRequestBuilder

    abstract class Abstract(
        protected val pathBuilder: RequestPathBuilder,
        protected val requestUrl: String,
        private val keyProvider: ApiKeyProvider
    ) : ServerRequestBuilder {

        protected abstract fun buildBody(text: String, fromLang: String, toLang: String): Any?

        protected abstract fun buildHeader(keyName: String, keyValue: String): Pair<String, String>

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
            val header =
                buildHeader(keyProvider.provideKeyHeaderName(), keyProvider.provideApiKey())
            builder.header(header.first, header.second)
            builder.setBody(buildBody(text, fromLang.code, toLang.code))
            builder.contentType(ContentType.Application.Json)
            return builder
        }
    }

    class Translation(
        keyProvider: ApiKeyProvider,
        pathBuilder: RequestPathBuilder = RequestPathBuilder.Base()
    ) : Abstract(
        pathBuilder = pathBuilder,
        keyProvider = keyProvider,
        requestUrl = "https://translation-api.translate.com/translate/v1/mt"
    ) {

        override fun buildHeader(keyName: String, keyValue: String) = Pair(keyName, keyValue)

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
    ) : Abstract(
        keyProvider = keyProvider,
        pathBuilder = pathBuilder,
        requestUrl = "https://api.deepl.com/v2/translate"
    ) {

        override fun buildHeader(keyName: String, keyValue: String) =
            Pair("Authorization", "$keyName $keyValue")

        override fun buildUrl(text: String, fromLang: String, toLang: String): String {
            return pathBuilder.setUrl(requestUrl)
                .addPath("text", text)
                .addPath("source_lang", fromLang)
                .addPath("target_lang", toLang)
                .build()
        }

        override fun buildBody(text: String, fromLang: String, toLang: String): Any {
            return Json.encodeToString(
                com.yuriisurzhykov.translator.translate.deepl.data.TranslateRequestModel.serializer(),
                com.yuriisurzhykov.translator.translate.deepl.data.TranslateRequestModel(
                    text, fromLang, toLang
                )
            )
        }

        override fun transform(
            builder: HttpRequestBuilder,
            text: String,
            fromLang: Language,
            toLang: Language
        ): HttpRequestBuilder {
            super.transform(builder, text, fromLang, toLang)
            builder.contentType(ContentType.Application.FormUrlEncoded)
            return builder
        }
    }
}