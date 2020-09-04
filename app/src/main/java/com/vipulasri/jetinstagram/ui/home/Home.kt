package com.vipulasri.jetinstagram.ui.home

import android.text.format.DateUtils
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.model.posts
import com.vipulasri.jetinstagram.model.stories
import com.vipulasri.jetinstagram.ui.components.defaultPadding
import com.vipulasri.jetinstagram.ui.components.diagonalGradientBorder
import com.vipulasri.jetinstagram.ui.components.horizontalPadding
import com.vipulasri.jetinstagram.ui.components.icon
import com.vipulasri.jetinstagram.ui.components.verticalPadding
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun Home() {
  Scaffold(
      topBar = { Toolbar() }) {
      ScrollableColumn {
        StoriesSection()
        Divider()
        PostList()
      }
  }
}

@Composable
private fun Toolbar() {
  TopAppBar(
      modifier = Modifier.fillMaxWidth(),
      backgroundColor = MaterialTheme.colors.background
  ) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalGravity = Alignment.CenterVertically
    ) {
      Icon(
          imageResource(id = R.drawable.ic_outlined_camera),
          modifier = Modifier.icon()
      )
      Box(
          modifier = Modifier.padding(12.dp),
          gravity = ContentGravity.Center
      ) {
        Icon(vectorResource(id = R.drawable.ic_instagram))
      }
      Icon(
          imageResource(id = R.drawable.ic_dm),
          modifier = Modifier.icon()
      )
    }
  }
}

@Composable
private fun StoriesSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
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
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun PostList() {
  LazyColumnFor(
      items = posts,
      modifier = Modifier.fillMaxHeight()
  ) { post ->
      PostView(post)
  }
}

@Composable
private fun PostView(post: Post) {
    Column {
      PostHeader(post)
      Box(
          modifier = Modifier.fillMaxWidth()
              .height(300.dp)
              .background(color = Color.LightGray)
      ) {
          CoilImage(
              data = post.image,
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
          )
      }
      PostFooter(post)
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
        Icon(Icons.Filled.MoreVert)
    }
}

@Composable
private fun PostFooter(post: Post) {
  PostFooterIconSection(post)
  PostFooterTextSection(post)
}

@Composable
private fun PostFooterIconSection(post: Post) {
  Row(
      modifier = Modifier.fillMaxWidth()
          .defaultPadding(),
      verticalGravity = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
  ) {
      Row(
          verticalGravity = Alignment.CenterVertically
      ) {

        LikeButton(isLiked = post.isLiked)
        Spacer(modifier = Modifier.width(10.dp))

        Icon(imageResource(id = R.drawable.ic_outlined_comment), modifier = Modifier.icon())
        Spacer(modifier = Modifier.width(10.dp))

        Icon(imageResource(id = R.drawable.ic_dm), modifier = Modifier.icon())

      }
      Icon(vectorResource(id = R.drawable.ic_outlined_bookmark), modifier = Modifier.icon())
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

@Composable
private fun LikeButton(isLiked: Boolean) {
  val likeIcon = if (isLiked) imageResource(id = R.drawable.ic_filled_favorite) else imageResource(
      id = R.drawable.ic_outlined_favorite
  )
  val likeTint = if (isLiked) Color.Red else contentColor()

  Icon(likeIcon, tint = likeTint, modifier = Modifier.icon())
}

private fun Long.getTimeElapsedText(): String {
  val now = System.currentTimeMillis()
  val time = this

  return DateUtils.getRelativeTimeSpanString(
      time, now, 0L, DateUtils.FORMAT_ABBREV_TIME).toString()
}