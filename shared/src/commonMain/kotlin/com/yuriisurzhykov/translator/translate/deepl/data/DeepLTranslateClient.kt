package com.yuriisurzhykov.translator.translate.deepl.data

import com.yuriisurzhykov.translator.core.data.ServerRequestBuilder
import com.yuriisurzhykov.translator.translate.common.data.AbstractTranslateClient
import io.ktor.client.*

class DeepLTranslateClient constructor(
    httpClient: HttpClient,
    requestTransform: ServerRequestBuilder
) : AbstractTranslateClient(httpClient, requestTransform, DeeplTranslateResponseMapper())