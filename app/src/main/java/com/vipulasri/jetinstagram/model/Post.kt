package com.vipulasri.jetinstagram.model

data class Post(
    val image: String,
    val userName: String,
    val userImage: String,
    val isLiked: Boolean = false,
    val likesCount: Int
)

private var _posts = ArrayList<Post>()
val posts: List<Post>
  get() = _posts

fun populatePosts() {
  _posts.clear()
  (0..9).forEach { index ->
    val post = Post(
        image = "https://source.unsplash.com/random/400x300?$index",
        userName = names[index],
        userImage = "https://randomuser.me/api/portraits/men/${index+1}.jpg",
        likesCount = index + 100
    )
    _posts.add(post)
  }
}