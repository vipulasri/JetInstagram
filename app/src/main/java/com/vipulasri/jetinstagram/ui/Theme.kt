package com.vipulasri.jetinstagram.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = lightColors(
    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200
)

private val LightColorPalette = darkColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200
)

@Composable
fun JetInstagramTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable() () -> Unit
) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
      colors = colors,
      typography = typography,
      shapes = shapes,
      content = content
  )
}