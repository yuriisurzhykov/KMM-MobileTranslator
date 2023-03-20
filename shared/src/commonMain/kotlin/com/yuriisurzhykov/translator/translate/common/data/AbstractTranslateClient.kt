package com.yuriisurzhykov.translator.translate.common.data

import com.yuriisurzhykov.translator.core.data.ServerRequestBuilder
import com.yuriisurzhykov.translator.language.data.Language
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*

abstract class AbstractTranslateClient(
    private val httpClient: HttpClient,
    private val requestTransform: ServerRequestBuilder<TranslateRequestModel>,
    private val responseMapper: TranslateResponseMapper
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
        return responseMapper.map(result)
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