package com.vipulasri.jetinstagram.ui.home

import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.data.PostsRepository
import com.vipulasri.jetinstagram.data.StoriesRepository
import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.model.Story
import com.vipulasri.jetinstagram.ui.components.bottomBarHeight
import com.vipulasri.jetinstagram.ui.components.icon
import kotlinx.coroutines.launch

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
      Spacer(modifier = Modifier.height(bottomBarHeight))
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
private fun PostList(
  posts: List<Post>,
  onDoubleClick: (Post) -> Unit,
  onLikeToggle: (Post) -> Unit
) {
  posts.forEach { post ->
    PostView(post, onDoubleClick, onLikeToggle)
  }
}