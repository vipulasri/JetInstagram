package com.vipulasri.jetinstagram.ui.home

import android.text.format.DateUtils
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.CoilImage
import com.vipulasri.jetinstagram.R.drawable
import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.ui.components.AnimLikeButton
import com.vipulasri.jetinstagram.ui.components.PhotoLikeAnimation
import com.vipulasri.jetinstagram.ui.components.PostIconButton
import com.vipulasri.jetinstagram.ui.components.defaultPadding
import com.vipulasri.jetinstagram.ui.components.horizontalPadding
import com.vipulasri.jetinstagram.ui.components.verticalPadding

@ExperimentalFoundationApi
@Composable fun PostView(
  post: Post,
  onDoubleClick: (Post) -> Unit,
  onLikeToggle: (Post) -> Unit
) {

  val photoLikeAnimation = remember { mutableStateOf(false) }

  Column {
    PostHeader(post)
      Box(
        modifier = Modifier.fillMaxWidth()
            .height(300.dp)
            .combinedClickable(
                onClick = { },
                onDoubleClick = {
                  photoLikeAnimation.value = true
                  onDoubleClick.invoke(post)
                }
            )
            .background(color = Color.LightGray)
    ) {
      CoilImage(
          data = post.image,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize(),
          contentDescription = null
      )
      PhotoLikeAnimation(
          modifier = Modifier.fillMaxSize(),
          startAnimation = photoLikeAnimation.value,
          onAnimationComplete = {
            photoLikeAnimation.value = false
          })
    }
    PostFooter(post, onLikeToggle)
    Divider()
  }
}

@Composable
private fun PostHeader(post: Post) {
  Row(
      modifier = Modifier.fillMaxWidth()
          .defaultPadding(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
          modifier = Modifier.size(30.dp)
              .background(color = Color.LightGray, shape = CircleShape)
              .clip(CircleShape)
      ) {
        CoilImage(
            data = post.user.image,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null
        )
      }
      Spacer(modifier = Modifier.width(10.dp))
      Text(text = post.user.username, style = MaterialTheme.typography.subtitle2)
    }
    Icon(Filled.MoreVert,"")
  }
}

@Composable
private fun PostFooter(
  post: Post,
  onLikeToggle: (Post) -> Unit
) {
  PostFooterIconSection(post, onLikeToggle)
  PostFooterTextSection(post)
}

@Composable
private fun PostFooterIconSection(
  post: Post,
  onLikeToggle: (Post) -> Unit
) {

  Row(
      modifier = Modifier.fillMaxWidth()
          .padding(horizontal = 5.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
      AnimLikeButton(post, onLikeToggle)

      PostIconButton {
        Icon(ImageBitmap.imageResource(id = drawable.ic_outlined_comment), "")
      }

      PostIconButton {
        Icon(ImageBitmap.imageResource(id = drawable.ic_dm), "")
      }
    }

    PostIconButton {
      Icon(ImageVector.vectorResource(id = drawable.ic_outlined_bookmark), "")
    }
  }
}

@Composable
private fun PostFooterTextSection(post: Post) {
  Column(
      modifier = Modifier.padding(
          start = horizontalPadding,
          end = horizontalPadding,
          bottom = verticalPadding
      )
  ) {
    Text(
        "${post.likesCount} likes",
        style = MaterialTheme.typography.subtitle2
    )

/*    ProvideEmphasis(EmphasisAmbient.current.medium) {
      Text(
          "View all ${post.commentsCount} comments",
          style = MaterialTheme.typography.caption
      )

      Spacer(modifier = Modifier.height(2.dp))

      Text(
          post.timeStamp.getTimeElapsedText(),
          style = MaterialTheme.typography.caption.copy(fontSize = 10.sp)
      )
    }*/

      Text(
          "View all ${post.commentsCount} comments",
          style = MaterialTheme.typography.caption
      )

      Spacer(modifier = Modifier.height(2.dp))

      Text(
          post.timeStamp.getTimeElapsedText(),
          style = MaterialTheme.typography.caption.copy(fontSize = 10.sp)
      )
  }
}

private fun Long.getTimeElapsedText(): String {
  val now = System.currentTimeMillis()
  val time = this

  return DateUtils.getRelativeTimeSpanString(
      time, now, 0L, DateUtils.FORMAT_ABBREV_TIME
  )
      .toString()
}