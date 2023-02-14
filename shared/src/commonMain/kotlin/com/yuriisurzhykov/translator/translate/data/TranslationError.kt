package com.yuriisurzhykov.translator.translate.data

abstract class TranslationError(error: String) :
    Exception("An error occurred when translating: $error") {

    class ServiceNotAvailable : TranslationError("ServiceUnavailable")
    class ClientError(errorCode: Int) : TranslationError("ClientError: $errorCode")
    class ServerError : TranslationError("ServerError")
    class UnknownError : TranslationError("UnknownError")

}