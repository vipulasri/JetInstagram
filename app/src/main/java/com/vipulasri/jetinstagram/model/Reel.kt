package com.vipulasri.jetinstagram.model

import android.net.Uri

data class Reel(
  val id: Int,
  private val video: String,
  val user: User,
  val isLiked: Boolean = false,
  val likesCount: Int,
  val commentsCount: Int
) {

  fun getVideoUrl(): Uri {
    return Uri.parse("asset:///${video}")
  }

}