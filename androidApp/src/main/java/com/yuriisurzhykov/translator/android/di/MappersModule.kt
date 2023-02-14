package com.yuriisurzhykov.translator.android.di

import com.yuriisurzhykov.translator.core.data.ApiKeyProvider
import com.yuriisurzhykov.translator.core.data.ServerRequestBuilder
import com.yuriisurzhykov.translator.history.domain.HistoryEntityListMapper
import com.yuriisurzhykov.translator.history.presentation.UiHistoryItemMapper
import com.yuriisurzhykov.translator.translate.data.TranslateRequestModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    @Singleton
    fun provideUiHistoryItemMapper(): UiHistoryItemMapper {
        return UiHistoryItemMapper.Base()
    }

    @Provides
    @Singleton
    fun provideHistoryEntityListMapper(): HistoryEntityListMapper {
        return HistoryEntityListMapper.Base()
    }

    @Provides
    @Singleton
    fun provideServerRequestBuilder(apiProvider: ApiKeyProvider): ServerRequestBuilder<TranslateRequestModel> {
        return ServerRequestBuilder.Base(apiProvider)
    }
}