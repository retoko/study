package ru.android.study.data.model

data class Actor(
  val id: Number,
  val movieId: Number,
  val name: String,
  val avatar: String
) {
  companion object {
    val empty = Actor(0, 0, "", "@drawable/ic_no_image")
  }
}
