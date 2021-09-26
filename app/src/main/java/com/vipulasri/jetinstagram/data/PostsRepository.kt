package com.vipulasri.jetinstagram.data

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.model.User
import com.vipulasri.jetinstagram.model.names
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
object PostsRepository {

  private val _posts = mutableStateOf<List<Post>>(emptyList())
  val posts: State<List<Post>> = _posts

  private fun populatePosts() {
    val posts = ArrayList<Post>()
    (0..9).forEach { index ->
      val post = Post(
          id = index + 1,
          image = "https://source.unsplash.com/random/400x300?$index",
          user = User(
              name = names[index],
              username = names[index],
              image = "https://randomuser.me/api/portraits/men/${index + 1}.jpg"
          ),
          likesCount = index + 100,
          commentsCount = index + 20,
          timeStamp = System.currentTimeMillis() - (index * 60000)
      )
      posts.add(post)
    }

    this._posts.value = posts
  }

  init {
    populatePosts()
  }

  suspend fun toggleLike(postId: Int) {
    updateLike(postId, true)
  }

  suspend fun performLike(postId: Int) {
    updateLike(postId, false)
  }

  private suspend fun updateLike(
    postId: Int,
    isToggle: Boolean
  ) {
    withContext(Dispatchers.IO) {
      val posts = _posts.value.toMutableList()
      for ((index, value) in posts.withIndex()) {
        if (value.id == postId) {

          val isLiked = if (isToggle) !value.isLiked else true

          // check if isLiked is same as previous state
          if (isLiked != value.isLiked) {
            val likesCount = if (isLiked) value.likesCount.plus(1) else value.likesCount.minus(1)

            posts[index] = value.copy(isLiked = isLiked, likesCount = likesCount)
          }

          break
        }
      }
      this@PostsRepository._posts.value = posts
    }
  }

}