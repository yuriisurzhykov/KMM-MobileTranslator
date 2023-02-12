package com.yuriisurzhykov.translator.core.data

import com.yuriisurzhykov.translator.translate.data.TranslateRequestModel
import io.ktor.client.request.*

class TranslateBodyRequestBuilder(private val body: TranslateRequestModel) :
    ServerRequestBuilder.Abstract<TranslateRequestModel>() {

    override fun transform(
        builder: HttpRequestBuilder
    ): HttpRequestBuilder {
        val updatedBuilder = super.transform(builder)
        updatedBuilder.setBody(body)
        return updatedBuilder
    }
}