package com.vipulasri.jetinstagram.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.vipulasri.jetinstagram.ui.components.diagonalGradientBorder

@Composable
fun StoryImage(imageUrl: String) {
  val shape = CircleShape
  Box(
    modifier = Modifier
      .diagonalGradientBorder(
        colors = listOf(
          Color(0xFFd71069),
          Color(0xFFe25d6a),
          Color(0xFFe9ad55),
        ),
        shape = shape,
        isFromRight = true
      )
  ) {
    Box(
      modifier = Modifier
        .size(66.dp)
        .padding(6.dp)
        .background(color = Color.LightGray, shape = shape)
        .clip(shape)
    ) {
      Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}
