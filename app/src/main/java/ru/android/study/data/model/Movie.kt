package ru.android.study.data.model

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
)
