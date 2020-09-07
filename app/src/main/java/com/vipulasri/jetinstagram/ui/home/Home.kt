package com.vipulasri.jetinstagram.ui.home

import android.text.format.DateUtils
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.vipulasri.jetinstagram.data.PostsRepository
import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.ui.components.defaultPadding
import com.vipulasri.jetinstagram.ui.components.diagonalGradientBorder
import com.vipulasri.jetinstagram.ui.components.horizontalPadding
import com.vipulasri.jetinstagram.ui.components.icon
import com.vipulasri.jetinstagram.ui.components.verticalPadding
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import com.vipulasri.jetinstagram.data.StoriesRepository
import com.vipulasri.jetinstagram.model.Story
import com.vipulasri.jetinstagram.ui.components.PostIconButton

@Composable
fun Home() {

  val coroutineScope = rememberCoroutineScope()

  Scaffold(
      topBar = { Toolbar() }) {
    val posts by PostsRepository.observePosts()
    val stories by StoriesRepository.observeStories()

    ScrollableColumn {
      StoriesSection(stories)
      Divider()
      PostList(posts,
        onDoubleClick = { post ->
          coroutineScope.launch {
            PostsRepository.performLike(post.id)
          }
        },
        onLikeToggle = { post ->
          coroutineScope.launch {
            PostsRepository.toggleLike(post.id)
          }
        }
      )
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
private fun StoriesSection(stories: List<Story>) {
  Column {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(text = "Stories", style = MaterialTheme.typography.subtitle2)
      Text(text = "Watch All", style = MaterialTheme.typography.subtitle2)
    }
    StoriesList(stories)
    Spacer(modifier = Modifier.height(10.dp))
  }
}

@Composable
private fun StoriesList(stories: List<Story>) {
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
private fun PostList(
  posts: List<Post>,
  onDoubleClick: (Post) -> Unit,
  onLikeToggle: (Post) -> Unit
) {
  posts.forEach { post ->
    PostView(post, onDoubleClick, onLikeToggle)
  }
}

@Composable
private fun PostView(
  post: Post,
  onDoubleClick: (Post) -> Unit,
  onLikeToggle: (Post) -> Unit
) {
  Column {
    PostHeader(post)
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(300.dp)
            .clickable(
                onClick = { },
                onDoubleClick = { onDoubleClick.invoke(post) },
                indication = null
            )
            .background(color = Color.LightGray)
    ) {
      CoilImage(
          data = post.image,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
      )
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
    Icon(Icons.Filled.MoreVert)
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
      modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
      verticalGravity = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Row(
        verticalGravity = Alignment.CenterVertically
    ) {
      LikeButton(post, onLikeToggle)

      PostIconButton {
        Icon(imageResource(id = R.drawable.ic_outlined_comment))
      }

      PostIconButton {
        Icon(imageResource(id = R.drawable.ic_dm))
      }
    }

    PostIconButton {
      Icon(vectorResource(id = R.drawable.ic_outlined_bookmark))
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

@Composable
private fun LikeButton(
  post: Post,
  onLikeClick: (Post) -> Unit
) {
  val likeIcon =
    if (post.isLiked) imageResource(id = R.drawable.ic_filled_favorite) else imageResource(
        id = R.drawable.ic_outlined_favorite
    )
  val likeTint = if (post.isLiked) Color.Red else contentColor()

  PostIconButton(onClick = { onLikeClick.invoke(post) }) {
    Icon(likeIcon, tint = likeTint)
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