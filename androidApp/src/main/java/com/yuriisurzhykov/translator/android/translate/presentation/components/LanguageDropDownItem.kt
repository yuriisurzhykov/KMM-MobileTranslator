package com.yuriisurzhykov.translator.android.translate.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.yuriisurzhykov.translator.language.presentation.UiLanguage

@Composable
fun LanguageDropDownItem(
    language: UiLanguage, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    DropdownMenuItem(onClick = onClick, modifier = modifier) {
        Text(text = language.emojiLanguageCode, modifier = Modifier.wrapContentWidth())
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = language.language.langName)
    }
}