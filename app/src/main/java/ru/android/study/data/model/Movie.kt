package ru.android.study.data.model

data class Movie(
  val id: Int,
  val title: String,
  val overview: String,
  val poster: String,
  val backdrop: String,
  val ratings: Float,
  val numberOfRatings: Int,
  val minimumAge: Int,
  val genres: List<Genre>
)
