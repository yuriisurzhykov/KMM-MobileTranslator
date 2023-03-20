package com.yuriisurzhykov.translator.translate.translate.data.remote

import com.yuriisurzhykov.translator.translate.common.data.TranslateResponseMapper


class TranslationTranslateResponseMapper :
    TranslateResponseMapper.Abstract<TranslateResponseModel>(TranslateResponseModel.serializer()) {
}