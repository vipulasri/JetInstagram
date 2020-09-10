package com.vipulasri.jetinstagram.model

data class User(
  val name: String,
  val userName: String,
  val image: String
)

val currentUser = User(
    name = "Vipul Asri",
    userName = "vipulasri",
    image = "https://s.gravatar.com/avatar/62a968f41c1feb83fd1cd142e7c043f3?s=200"
)