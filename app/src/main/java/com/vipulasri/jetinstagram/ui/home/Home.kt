package com.vipulasri.jetinstagram.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
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
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.R.drawable
import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.model.posts
import com.vipulasri.jetinstagram.model.stories
import com.vipulasri.jetinstagram.ui.components.diagonalGradientBorder
import com.vipulasri.jetinstagram.ui.components.icon
import com.vipulasri.jetinstagram.ui.theme.grey50
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
      backgroundColor = MaterialTheme.colors.surface,
  ) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalGravity = Alignment.CenterVertically
    ) {
      Image(
          modifier = Modifier.icon(),
          asset = imageResource(id = drawable.ic_outlined_camera))
      Box(
          modifier = Modifier.padding(12.dp),
          gravity = ContentGravity.Center) {
        Image(vectorResource(id = R.drawable.ic_instagram))
      }
      Image(
          modifier = Modifier.icon(),
          asset = imageResource(id = drawable.ic_dm)
      )
    }
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
          modifier = Modifier.fillMaxWidth().height(300.dp)
              .background(color = Color.LightGray)
      ) {
          CoilImage(
              data = post.image,
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize())
      }
      PostFooter(post)
      Divider()
    }
}

@Composable
private fun PostHeader(post: Post) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 12.dp),
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
                    modifier = Modifier.fillMaxSize())
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = post.userName, style = MaterialTheme.typography.subtitle2)
        }
        Icon(Icons.Filled.MoreVert)
    }
}

@Composable
private fun PostFooter(post: Post) {
  Row(
      modifier = Modifier.fillMaxWidth()
          .padding(horizontal = 10.dp, vertical = 12.dp),
      verticalGravity = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
  ) {
      Row(
          verticalGravity = Alignment.CenterVertically
      ) {

        LikeButton(isLiked = post.isLiked)
        Spacer(modifier = Modifier.width(10.dp))

        Icon(imageResource(id = drawable.ic_outlined_comment), modifier = Modifier.icon())
        Spacer(modifier = Modifier.width(10.dp))

        Icon(imageResource(id = drawable.ic_dm), modifier = Modifier.icon())

      }
      Icon(vectorResource(id = drawable.ic_outlined_bookmark), modifier = Modifier.icon())
  }
}

@Composable
private fun LikeButton(isLiked: Boolean) {
  val likeIcon = if (isLiked) imageResource(id = drawable.ic_filled_favorite) else imageResource(id = drawable.ic_outlined_favorite)
  val likeTint = if (isLiked) Color.Red else Color.Black

  Icon(likeIcon, tint = likeTint, modifier = Modifier.icon())
}