package com.yuriisurzhykov.translator.translate.data

import com.yuriisurzhykov.translator.language.data.Language

/**
 * Common interface for declare what function should be in iOS and Android specific packages,
 * that implements feature to translate text.
 */
interface TranslateClient {

    /**
     * @param fromLang The [Language] you need to translate text from.
     * @param toLang The [Language] you need to translate to.
     * @return The [TranslateDataResult] that should be one of results declared in the interface.
     */
    suspend fun translate(
        text: String, fromLang: Language, toLang: Language
    ): TranslateDataResult
}