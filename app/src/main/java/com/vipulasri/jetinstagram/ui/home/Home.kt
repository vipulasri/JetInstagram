package com.vipulasri.jetinstagram.ui.home

import android.util.Log
import androidx.annotation.Px
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.Companion
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset.Companion.Zero
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.HorizontalGradient
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.RadialGradient
import androidx.compose.ui.graphics.VerticalGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntSize.Companion.Zero
import androidx.compose.ui.unit.dp
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.R.drawable
import com.vipulasri.jetinstagram.model.stories
import com.vipulasri.jetinstagram.ui.components.diagonalGradientBorder
import com.vipulasri.jetinstagram.ui.theme.grey100
import com.vipulasri.jetinstagram.ui.theme.grey50
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun Home() {
  Scaffold(
      topBar = { Toolbar() }) {
      Column {
          StoriesSection()
          Divider()
      }
  }
}

@Composable
private fun Toolbar() {
  TopAppBar(
      modifier = Modifier.fillMaxWidth() + Modifier.wrapContentSize(Alignment.Center),
      backgroundColor = MaterialTheme.colors.background
  ) {
    Image(
        modifier = Modifier.padding(18.dp),
        asset = imageResource(id = drawable.ic_camera))
    Box(
        modifier = Modifier.padding(10.dp),
        gravity = ContentGravity.Center) {
      Image(vectorResource(id = R.drawable.ic_instagram))
    }
    Image(
        modifier = Modifier.padding(16.dp),
        asset = imageResource(id = drawable.ic_instagram_dm)
    )
  }
}

@Composable
private fun StoriesSection() {
    Column(
        modifier = Modifier.background(color = grey50)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Stories", style = MaterialTheme.typography.subtitle2)
            Text(text = "Watch All", style = MaterialTheme.typography.subtitle2)
        }
        StoriesList()
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun StoriesList() {
    LazyRowFor(
        items = stories
    ) { story ->
        Column(
            horizontalGravity = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            StoryImage(imageUrl = story.image)
            Spacer(modifier = Modifier.height(5.dp))
            Text(story.name, style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
private fun StoryImage(imageUrl: String) {
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
                modifier = Modifier.fillMaxSize())
        }
    }
}