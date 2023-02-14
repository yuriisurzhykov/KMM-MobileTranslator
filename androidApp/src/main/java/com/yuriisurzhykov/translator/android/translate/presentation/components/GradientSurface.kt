package com.yuriisurzhykov.translator.android.translate.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.yuriisurzhykov.translator.core.presentation.Colors

fun Modifier.gradientSurface(): Modifier = composed {
    if (isSystemInDarkTheme()) {
        Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(Colors.DarkBlue),
                    Color(Colors.DarkerBlue)
                )
            )
        )
    } else {
        Modifier.background(MaterialTheme.colors.surface)
    }
}