package com.yuriisurzhykov.translator.translate.presentation

import com.yuriisurzhykov.translator.history.domain.HistoryDataSource
import com.yuriisurzhykov.translator.language.data.Language
import com.yuriisurzhykov.translator.translate.data.TranslateClient
import com.yuriisurzhykov.translator.translate.data.TranslationError

interface TranslateUseCase {

    suspend fun translate(
        text: String, fromLanguage: Language, toLanguage: Language
    ): TranslateResult<*>

    abstract class Abstract(
        private val remoteClient: TranslateClient,
        private val cacheDataSource: HistoryDataSource,
        private val mapper: TranslateResponseMapper
    ) : TranslateUseCase {

        override suspend fun translate(
            text: String, fromLanguage: Language, toLanguage: Language
        ): TranslateResult<*> {
            return try {
                val response = remoteClient.translate(text, fromLanguage, toLanguage)
                val mapped = mapper.map(text, fromLanguage, toLanguage, response)
                cacheDataSource.insert(mapped)
                TranslateResult.TranslationSuccess(response)
            } catch (e: TranslationError) {
                TranslateResult.TranslateError(e)
            }
        }
    }

    class Base(remoteClient: TranslateClient, cacheDataSource: HistoryDataSource) :
        Abstract(remoteClient, cacheDataSource, TranslateResponseMapper.Base())

}