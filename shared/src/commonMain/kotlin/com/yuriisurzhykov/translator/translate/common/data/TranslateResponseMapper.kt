package com.yuriisurzhykov.translator.translate.common.data

import com.yuriisurzhykov.translator.core.SuspendMapper
import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json

interface TranslateResponseMapper : SuspendMapper<HttpResponse, TranslateDataResult> {

    abstract class Abstract<T : Any> constructor(
        private val deserializationStrategy: DeserializationStrategy<T>
    ) : TranslateResponseMapper {
        override suspend fun map(input: HttpResponse): TranslateDataResult {
            return TranslateDataResult.TranslationSuccess(
                Json.decodeFromString(deserializationStrategy, input.body()).toString()
            )
        }
    }
}