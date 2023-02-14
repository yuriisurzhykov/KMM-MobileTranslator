package com.yuriisurzhykov.translator.android.translate.presentation.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yuriisurzhykov.translator.language.presentation.UiLanguage

@Composable
fun SmallLanguageIcon(
    language: UiLanguage, modifier: Modifier = Modifier
) {
    Text(
        text = language.emojiLanguageCode,
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
    )
}