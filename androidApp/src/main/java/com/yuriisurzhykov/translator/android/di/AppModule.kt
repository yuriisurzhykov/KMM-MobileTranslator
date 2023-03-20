package com.yuriisurzhykov.translator.android.di

import android.app.Application
import com.squareup.sqldelight.db.SqlDriver
import com.yuriisurzhykov.translator.android.core.data.TranslationApiKeyProvider
import com.yuriisurzhykov.translator.core.data.ApiKeyProvider
import com.yuriisurzhykov.translator.core.data.ServerRequestBuilder
import com.yuriisurzhykov.translator.database.TranslateDatabase
import com.yuriisurzhykov.translator.history.data.SqlDelightHistoryDataSource
import com.yuriisurzhykov.translator.history.domain.HistoryDataSource
import com.yuriisurzhykov.translator.history.domain.HistoryEntityListMapper
import com.yuriisurzhykov.translator.translate.translate.data.remote.TranslationTranslateClient
import com.yuriisurzhykov.translator.translate.common.data.TranslateClient
import com.yuriisurzhykov.translator.translate.common.data.TranslateRequestModel
import com.yuriisurzhykov.translator.translate.common.data.DatabaseDriverFactory
import com.yuriisurzhykov.translator.translate.common.data.HttpClientFactory
import com.yuriisurzhykov.translator.translate.common.domain.TranslateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideTranslateClient(
        httpClient: HttpClient, requestBuilder: ServerRequestBuilder<TranslateRequestModel>
    ): TranslateClient {
        return TranslationTranslateClient(httpClient, requestBuilder)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(
        driver: SqlDriver, mapper: HistoryEntityListMapper
    ): HistoryDataSource {
        return SqlDelightHistoryDataSource(mapper = mapper, db = TranslateDatabase.invoke(driver))
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        remoteClient: TranslateClient, dataSource: HistoryDataSource
    ): TranslateUseCase {
        return TranslateUseCase.Base(remoteClient, dataSource)
    }

    @Provides
    @Singleton
    fun provideApiKeyProvider(): ApiKeyProvider {
        return TranslationApiKeyProvider()
    }
}