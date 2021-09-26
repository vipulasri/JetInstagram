package com.vipulasri.jetinstagram.ui.reels

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.data.ReelsRepository
import com.vipulasri.jetinstagram.model.Reel
import com.vipulasri.jetinstagram.ui.components.*

@Composable
fun Reels() {
    Box(modifier = Modifier.background(color = Color.Black)) {
    ReelsList()
    ReelsHeader()
  }
}

@Composable
private fun ReelsHeader() {
  Row(
      modifier = Modifier.fillMaxWidth()
          .defaultPadding(),
      horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text("Reels", color = Color.White)
    Icon(
        ImageBitmap.imageResource(id = R.drawable.ic_outlined_camera),
        tint = Color.White,
        modifier = Modifier.icon(),
        contentDescription = ""
    )
  }
}

@Composable
private fun ReelsList() {
  val reels = ReelsRepository.getReels()

/*
  val configuration = ConfigurationAmbient.current
  val reelsHeight = configuration.screenHeightDp.dp - bottomBarHeight
*/

    LazyColumn{

        itemsIndexed(reels) { _, reel ->
            Box(
                modifier = Modifier.fillParentMaxSize(),
            ) {

                VideoPlayer(uri = reel.getVideoUrl())

                Column(
                    modifier = Modifier.align(Alignment.BottomStart),
                ) {
                    ReelFooter(reel = reel)
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun ReelFooter(
  reel: Reel
) {
  Column(
      modifier = Modifier.defaultPadding(),
      verticalArrangement = Arrangement.Center
  ) {

    // user data
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
      Image(
        painter = rememberImagePainter(reel.user.image),
        contentDescription = null,
        modifier = Modifier.size(20.dp)
          .background(color = Color.Gray, shape = CircleShape)
          .clip(CircleShape)
      )
      Spacer(modifier = Modifier.width(horizontalPadding))
      Text(
          text = reel.user.username,
          color = Color.White,
          style = MaterialTheme.typography.subtitle2
      )
      Spacer(modifier = Modifier.width(horizontalPadding))
      Canvas(modifier = Modifier.size(5.dp), onDraw = {
        drawCircle(
            color = Color.White,
            radius = 8f
        )
      })
      Spacer(modifier = Modifier.width(horizontalPadding))
      Text(
          text = "Follow",
          color = Color.White,
          style = MaterialTheme.typography.subtitle2
      )
    }

    Spacer(modifier = Modifier.height(verticalPadding))

    // actions
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
          verticalAlignment = Alignment.CenterVertically
      ) {
        UserAction(R.drawable.ic_outlined_favorite)
        Spacer(modifier = Modifier.width(horizontalPadding))
        UserAction(R.drawable.ic_outlined_comment)
        Spacer(modifier = Modifier.width(horizontalPadding))
        UserAction(R.drawable.ic_dm)
      }

      Row(
          verticalAlignment = Alignment.CenterVertically
      ) {
        UserActionWithText(
            drawableRes = R.drawable.ic_outlined_favorite,
            text = reel.likesCount.toString()
        )
        Spacer(modifier = Modifier.width(horizontalPadding))
        UserActionWithText(
            drawableRes = R.drawable.ic_outlined_favorite,
            text = reel.commentsCount.toString()
        )
      }
    }

  }
}

@Composable
private fun UserAction(@DrawableRes drawableRes: Int) {
  Icon(
      ImageBitmap.imageResource(id = drawableRes),
      tint = Color.White,
      modifier = Modifier.icon(),
      contentDescription = ""
  )
}

@Composable
private fun UserActionWithText(
  @DrawableRes drawableRes: Int,
  text: String
) {
  Icon(
      ImageBitmap.imageResource(id = drawableRes),
      tint = Color.White,
      modifier = Modifier.size(18.dp),
      contentDescription = ""
  )
  Spacer(modifier = Modifier.width(horizontalPadding / 2))
  Text(
      text = text,
      color = Color.White,
      style = MaterialTheme.typography.body2
  )
}

@Composable
private fun LoadingIndicator(modifier: Modifier) {
  Box(
      modifier = modifier,
      contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator(
        color = Color.Gray,
        strokeWidth = 2.dp
    )
  }
}