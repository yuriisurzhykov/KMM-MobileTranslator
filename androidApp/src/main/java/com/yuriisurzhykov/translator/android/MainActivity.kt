package com.yuriisurzhykov.translator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yuriisurzhykov.translator.Greeting
import com.yuriisurzhykov.translator.android.translate.presentation.components.TranslateRoot
import com.yuriisurzhykov.translator.android.translate.presentation.components.TranslateScreen
import com.yuriisurzhykov.translator.translate.presentation.TranslateState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TranslatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    TranslateRoot()
                }
            }
        }
    }
}

@Preview
@Composable
fun TranslateRootPreview() {
    TranslateScreen(state = TranslateState(), onEvent = {})
}