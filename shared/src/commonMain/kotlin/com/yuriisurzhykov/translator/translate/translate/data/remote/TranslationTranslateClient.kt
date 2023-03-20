package com.yuriisurzhykov.translator.translate.translate.data.remote

import com.yuriisurzhykov.translator.core.data.ServerRequestBuilder
import com.yuriisurzhykov.translator.translate.common.data.*
import io.ktor.client.*

class TranslationTranslateClient(
    httpClient: HttpClient,
    requestTransform: ServerRequestBuilder
) : AbstractTranslateClient(httpClient, requestTransform, TranslationTranslateResponseMapper())