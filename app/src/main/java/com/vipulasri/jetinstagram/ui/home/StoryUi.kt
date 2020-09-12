package com.vipulasri.jetinstagram.ui.home

import androidx.compose.foundation.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.vipulasri.jetinstagram.ui.components.diagonalGradientBorder
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun StoryImage(imageUrl: String) {
  val shape = CircleShape
  Box(
      modifier = Modifier
          .diagonalGradientBorder(
              colors = listOf(
                  Color(0xFFd6249f),
                  Color(0xFFfd5949),
                  Color(0xFFfdf497),
              ),
              shape = shape,
              isFromRight = true
          )
  ) {
    Box(
        modifier = Modifier.preferredSize(60.dp)
            .padding(3.dp)
            .background(color = Color.LightGray, shape = shape)
            .clip(shape)
    ) {
      CoilImage(
          data = imageUrl,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
      )
    }
  }
}
