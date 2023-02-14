package com.yuriisurzhykov.translator.translate.data

import com.yuriisurzhykov.translator.core.data.ServerRequestBuilder
import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.translate.data.remote.TranslateRequestModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*

class KtorTranslateClient(
    private val httpClient: HttpClient,
    private val requestTransform: ServerRequestBuilder<TranslateRequestModel>
) : TranslateClient {

    override suspend fun translate(
        text: String, fromLang: Language, toLang: Language
    ): TranslateDataResult {
        val result = try {
            httpClient.post { requestTransform.transform(this, text, fromLang, toLang) }
        } catch (e: IOException) {
            throw TranslationError.ServiceNotAvailable()
        }
        checkForResponseError(result.status.value)
        return TranslateDataResult.TranslationSuccess(result.body())
    }

    private fun checkForResponseError(code: Int) {
        when (code) {
            in 200..299 -> Unit
            in 400..499 -> throw TranslationError.ClientError(code)
            500 -> throw TranslationError.ServerError()
            else -> throw TranslationError.UnknownError()
        }
    }
}