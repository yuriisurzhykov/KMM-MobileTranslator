package com.yuriisurzhykov.translator.android.core.data

import com.yuriisurzhykov.translator.android.BuildConfig
import com.yuriisurzhykov.translator.core.data.ApiKeyProvider

class ApiKeyFileProvider : ApiKeyProvider {

    override fun provideApiKey(): String = BuildConfig.TRANSLATE_API_KEY
}