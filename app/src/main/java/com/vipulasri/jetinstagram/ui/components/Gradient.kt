package com.vipulasri.jetinstagram.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.diagonalGradientTint(
  colors: List<Color>,
  blendMode: BlendMode
) = drawWithContent {
  drawContent()
  drawRect(
    brush = Brush.linearGradient(colors),
    blendMode = blendMode
  )
}

fun Modifier.offsetGradientBackground(
  colors: List<Color>,
  width: Float,
  offset: Float = 0f
) = background(
  Brush.horizontalGradient(
    colors,
    startX = -offset,
    endX = width - offset,
    tileMode = TileMode.Mirror
  )
)

fun Modifier.diagonalGradientBorder(
  colors: List<Color>,
  borderSize: Dp = 2.dp,
  shape: Shape,
  isFromRight: Boolean = false
) = composed {

  val (start, end) = if (isFromRight) {
    Pair(
      Offset(100f, 0.0f),
      Offset(0.0f, 100.0f)
    )
  } else {
    Pair(Offset.Zero, Offset.Infinite)
  }

  border(
    width = borderSize,
    brush = Brush.linearGradient(colors = colors, start = start, end = end),
    shape = shape
  )
}

fun Modifier.fadeInDiagonalGradientBorder(
  showBorder: Boolean,
  colors: List<Color>,
  borderSize: Dp = 2.dp,
  shape: Shape
) = composed {
  val animatedColors = List(colors.size) { i ->
    animateColorAsState(if (showBorder) colors[i] else colors[i].copy(alpha = 0f)).value
  }
  diagonalGradientBorder(
    colors = animatedColors,
    borderSize = borderSize,
    shape = shape
  )
}