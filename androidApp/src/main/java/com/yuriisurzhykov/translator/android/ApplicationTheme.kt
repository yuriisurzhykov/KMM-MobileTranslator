package com.yuriisurzhykov.translator.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TranslatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        com.yuriisurzhykov.translator.android.core.theme.darkColors
    } else {
        com.yuriisurzhykov.translator.android.core.theme.darkColors
    }
    val openSans = FontFamily(
        Font(resId = R.font.opensans_light, weight = FontWeight.Light),
        Font(resId = R.font.opensans_regular, weight = FontWeight.Normal),
        Font(resId = R.font.opensans_medium, weight = FontWeight.Medium),
        Font(resId = R.font.opensans_bold, weight = FontWeight.Bold)
    )
    val typography = Typography(
        h1 = TextStyle(
            fontFamily = openSans,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        ),
        h2 = TextStyle(
            fontFamily = openSans,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        h3 = TextStyle(
            fontFamily = openSans,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),
        body1 = TextStyle(
            fontFamily = openSans,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        body2 = TextStyle(
            fontFamily = openSans,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
