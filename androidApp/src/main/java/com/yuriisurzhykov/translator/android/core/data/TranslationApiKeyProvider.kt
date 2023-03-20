package com.yuriisurzhykov.translator.android.core.data

import com.yuriisurzhykov.translator.android.BuildConfig
import com.yuriisurzhykov.translator.core.data.ApiKeyProvider

class TranslationApiKeyProvider : ApiKeyProvider {

    override fun provideApiKey(): String = BuildConfig.TRANSLATE_API_KEY
    override fun provideKeyHeaderName() = "x-api-key"
}