package com.yuriisurzhykov.translator.android.core.data

import com.yuriisurzhykov.translator.android.BuildConfig
import com.yuriisurzhykov.translator.core.data.ApiKeyProvider

class DeepLApiKeyProvider : ApiKeyProvider {
    override fun provideApiKey(): String = BuildConfig.DEEPL_API_KEY

    override fun provideKeyHeaderName() = "DeepL-Auth-Key"
}