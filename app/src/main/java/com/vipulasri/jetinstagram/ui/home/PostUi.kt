package com.vipulasri.jetinstagram.ui.home

import android.text.format.DateUtils
import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vipulasri.jetinstagram.R.drawable
import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.ui.components.AnimLikeButton
import com.vipulasri.jetinstagram.ui.components.PhotoLikeAnimation
import com.vipulasri.jetinstagram.ui.components.PostIconButton
import com.vipulasri.jetinstagram.ui.components.defaultPadding
import com.vipulasri.jetinstagram.ui.components.horizontalPadding
import com.vipulasri.jetinstagram.ui.components.verticalPadding
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable fun PostView(
  post: Post,
  onDoubleClick: (Post) -> Unit,
  onLikeToggle: (Post) -> Unit
) {

  val photoLikeAnimation = remember { mutableStateOf(false) }

  Column {
    PostHeader(post)
    Stack(
        modifier = Modifier.fillMaxWidth()
            .height(300.dp)
            .clickable(
                onClick = { },
                onDoubleClick = {
                  photoLikeAnimation.value = true
                  onDoubleClick.invoke(post)
                },
                indication = null
            )
            .background(color = Color.LightGray)
    ) {
      CoilImage(
          data = post.image,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
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
      verticalGravity = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Row(
        verticalGravity = Alignment.CenterVertically
    ) {
      Box(
          modifier = Modifier.preferredSize(30.dp)
              .background(color = Color.LightGray, shape = CircleShape)
              .clip(CircleShape)
      ) {
        CoilImage(
            data = post.userImage,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
      }
      Spacer(modifier = Modifier.width(10.dp))
      Text(text = post.userName, style = MaterialTheme.typography.subtitle2)
    }
    Icon(Filled.MoreVert)
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
      verticalGravity = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Row(
        verticalGravity = Alignment.CenterVertically
    ) {
      AnimLikeButton(post, onLikeToggle)

      PostIconButton {
        Icon(imageResource(id = drawable.ic_outlined_comment))
      }

      PostIconButton {
        Icon(imageResource(id = drawable.ic_dm))
      }
    }

    PostIconButton {
      Icon(vectorResource(id = drawable.ic_outlined_bookmark))
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

    ProvideEmphasis(EmphasisAmbient.current.medium) {
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
}

private fun Long.getTimeElapsedText(): String {
  val now = System.currentTimeMillis()
  val time = this

  return DateUtils.getRelativeTimeSpanString(
      time, now, 0L, DateUtils.FORMAT_ABBREV_TIME
  )
      .toString()
}