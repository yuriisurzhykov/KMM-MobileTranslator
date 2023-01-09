package com.yuriisurzhykov.translator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform