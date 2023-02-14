package com.yuriisurzhykov.translator.android.translate.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yuriisurzhykov.translator.android.R
import com.yuriisurzhykov.translator.android.core.theme.LightBlue
import com.yuriisurzhykov.translator.language.presentation.UiLanguage
import com.yuriisurzhykov.translator.language.presentation.UiLanguageDataStore

@Composable
fun LanguageDropDown(
    language: UiLanguage,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectLanguage: (UiLanguage) -> Unit,
    modifier: Modifier = Modifier,
    languagesDataStore: UiLanguageDataStore = UiLanguageDataStore()
) {

    Box(modifier = modifier) {
        DropdownMenu(
            expanded = isOpen, onDismissRequest = onDismiss
        ) {
            languagesDataStore.getAllLanguages().forEach { language ->
                LanguageDropDownItem(
                    language = language,
                    onClick = { onSelectLanguage(language) },
                    /*modifier = Modifier.fillMaxWidth()*/
                )
            }
        }
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = language.emojiLanguageCode, modifier = Modifier.wrapContentWidth())
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = language.language.langName,
                color = LightBlue
            )
            Icon(
                imageVector = if (isOpen) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = if (isOpen) stringResource(id = R.string.close)
                else stringResource(id = R.string.open),
                tint = LightBlue,
                modifier = Modifier.size(30.dp)
            )
        }
    }

}