package com.yuriisurzhykov.translator.core.data

interface RequestPathBuilder {
    fun setUrl(url: String): RequestPathBuilder
    fun addPath(key: String, value: Any): RequestPathBuilder
    fun build(): String

    class Base : RequestPathBuilder {

        private val fullUrl = StringBuilder()

        override fun setUrl(url: String): RequestPathBuilder {
            fullUrl.append(url)
            return this
        }

        override fun addPath(key: String, value: Any): RequestPathBuilder {
            if (!fullUrl.contains("?")) {
                fullUrl.append("?")
            } else {
                fullUrl.append("&")
            }
            fullUrl.append(key).append("=").append(value)
            return this
        }

        override fun build(): String {
            return fullUrl.toString().also { fullUrl.clear() }
        }
    }
}