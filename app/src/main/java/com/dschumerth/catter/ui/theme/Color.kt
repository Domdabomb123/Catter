package com.dschumerth.catter.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

// /* With # */
//#DDDDDD, #161E29, #123248, #171A1F, #13151B, #46494F, #79A6AF, #FFFFFF, #FFBFA9

val Blue = Color(0xFF124B72)

val LightBlack = Color(0xFF222530)
val DarkBlack = Color(0xFF13151B)
val White = Color(0xFFdddddd)

// Rally is always dark themed.
val ColorPalette = darkColorScheme(
    primary = DarkBlack,
    secondary = Blue,
    surface = LightBlack,
    onSurface = White,
    background = DarkBlack,
    onBackground = White
)
