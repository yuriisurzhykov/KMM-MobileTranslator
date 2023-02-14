package com.yuriisurzhykov.translator.android.core.data

import android.content.Context
import android.util.Base64
import com.yuriisurzhykov.translator.core.data.ApiKeyProvider
import java.nio.charset.StandardCharsets

class ApiKeyFileProvider(private val context: Context) : ApiKeyProvider {

    override fun provideApiKey(): String {
        return context.assets.open("apikey.base64").bufferedReader().use { reader ->
            reader.readLine().let { input ->
                Base64.decode(input, Base64.DEFAULT).toString(StandardCharsets.UTF_8)
            }
        }
    }
}