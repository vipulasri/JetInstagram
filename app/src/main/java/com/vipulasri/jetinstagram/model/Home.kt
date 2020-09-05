package com.vipulasri.jetinstagram.model

data class Post(
  val id: Int,
  val image: String,
  val userName: String,
  val userImage: String,
  val isLiked: Boolean = false,
  val likesCount: Int,
  val commentsCount: Int,
  val timeStamp: Long
)

data class Story(
  val image: String,
  val name: String,
  val isSeen: Boolean = false
)

val names = arrayOf(
    "storee",
    "nianyc",
    "opioke",
    "ashoke",
    "dark_emarlds",
    "bedtan",
    "shrish",
    "matdo",
    "phillsohn",
    "deitch"
)