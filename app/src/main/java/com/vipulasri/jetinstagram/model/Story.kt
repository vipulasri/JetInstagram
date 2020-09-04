package com.vipulasri.jetinstagram.model

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

private var _stories = ArrayList<Story>()
val stories: List<Story>
  get() = _stories

fun populateStories() {
  _stories.clear()
  _stories.add(
      Story(
          image = currentUser.image,
          name = currentUser.userName
      )
  )
  (0..9).forEach { index ->
    val story = Story(
        image = "https://randomuser.me/api/portraits/men/${index+1}.jpg",
        name = names[index]
    )
    _stories.add(story)
  }

}