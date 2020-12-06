package ru.android.study.data.model

import ru.android.study.R

data class Actor(
  val id: Number,
  val movieId: Number,
  val name: String,
  val avatar: Int
) {
  companion object {
    val empty = Actor(0, 0, "", R.drawable.ic_no_image)
  }
}
