package ru.android.study.data.model

import ru.android.study.R

data class Movie(
  val id: Int,
  val name: String,
  val liked: Boolean,
  val genre: String,
  var rating: Float,
  val reviewsCount: Int,
  val ageLimit: Int,
  val storyline: String,
  val actorIds: List<Int>,
  val filmDuration: Int,
  val miniature: Int,
  val background: Int
) {
  companion object {
    val empty = Movie(
      0,
      "",
      false,
      "",
      0.0f,
      0,
      0,
      "",
      listOf(0),
      0,
      R.drawable.ic_no_image,
      R.drawable.ic_no_image
    )
  }
}
