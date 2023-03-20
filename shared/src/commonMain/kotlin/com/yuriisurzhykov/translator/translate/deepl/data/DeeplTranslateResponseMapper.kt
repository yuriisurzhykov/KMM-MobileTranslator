package com.yuriisurzhykov.translator.translate.deepl.data

import com.yuriisurzhykov.translator.translate.common.data.TranslateResponseMapper

class DeeplTranslateResponseMapper :
    TranslateResponseMapper.Abstract<TranslateResponseList>(TranslateResponseList.serializer())