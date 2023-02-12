package com.yuriisurzhykov.translator.translate.domain

import com.yuriisurzhykov.translator.core.data.ServerRequestBuilder
import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.translate.data.TranslateClient
import com.yuriisurzhykov.translator.translate.data.TranslateRequestModel
import com.yuriisurzhykov.translator.translate.data.TranslateResult
import com.yuriisurzhykov.translator.translate.data.TranslationError
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*

class KtorTranslateClient constructor(
    private val httpClient: HttpClient,
    private val requestTransform: ServerRequestBuilder<TranslateRequestModel>
) : TranslateClient {

    override suspend fun translate(
        fromLang: Language, toLang: Language, translateText: String
    ): TranslateResult {
        val result = try {
            httpClient.post { requestTransform.transform(this) }
        } catch (e: IOException) {
            throw TranslationError.ServiceNotAvailable()
        }
        checkForResponseError(result.status.value)
        return TranslateResult.TranslationSuccess(result.body())
    }

    private fun checkForResponseError(code: Int) {
        when (code) {
            in 200..299 -> Unit
            in 400..499 -> throw TranslationError.ClientError()
            500 -> throw TranslationError.ServerError()
            else -> throw TranslationError.UnknownError()
        }
    }
}