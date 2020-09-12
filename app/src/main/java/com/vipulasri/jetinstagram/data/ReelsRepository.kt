package com.vipulasri.jetinstagram.data

import com.vipulasri.jetinstagram.model.Reel
import com.vipulasri.jetinstagram.model.User
import com.vipulasri.jetinstagram.model.names
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
object ReelsRepository {

  private val reels = arrayListOf<Reel>()

  private fun populateReels() {
    val _reels = ArrayList<Reel>()
    (0..9).forEach { index ->
      val post = Reel(
          id = index + 1,
          video = videos[index],
          user = User(
              name = names[index],
              username = names[index],
              image = "https://randomuser.me/api/portraits/men/${index + 1}.jpg"
          ),
          likesCount = index + 100,
          commentsCount = index + 20
      )
      _reels.add(post)
    }

    reels.clear()
    reels.addAll(_reels)
  }

  init {
    populateReels()
  }

  fun getReels(): List<Reel> = reels
}

private val videos = listOf(
    "food.mp4",
    "soap-bubbles.mp4",
    "castle.mp4",
    "lake.mp4",
    "icecream.mp4",
    "soap-bubbles.mp4",
    "castle.mp4",
    "lake.mp4",
    "icecream.mp4",
    "soap-bubbles.mp4",
    "castle.mp4",
    "lake.mp4",
    "icecream.mp4",
)