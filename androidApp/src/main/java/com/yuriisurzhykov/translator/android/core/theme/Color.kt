package com.yuriisurzhykov.translator.android.core.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.yuriisurzhykov.translator.core.presentation.Colors

val Accent = Color(0xFF5CC5A4)
val AccentLight = Color(0xFF85A08F)
val LightBlueGrey = Color(0xFFF6F6F4)
val TextBlack = Color(Colors.TextBlack)
val DarkGrey = Color(0xFF282B31)

val lightColors = lightColors(
    primary = Accent,
    background = LightBlueGrey,
    onPrimary = Color.White,
    onBackground = TextBlack,
    surface = Color.White,
    onSurface = TextBlack
)

val darkColors = darkColors(
    primary = Accent,
    background = DarkGrey,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = DarkGrey,
    onSurface = Color.White
)