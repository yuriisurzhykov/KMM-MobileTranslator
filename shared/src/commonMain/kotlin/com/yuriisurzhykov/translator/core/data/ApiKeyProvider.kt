package com.yuriisurzhykov.translator.core.data

interface ApiKeyProvider {
    fun provideApiKey(): String
}