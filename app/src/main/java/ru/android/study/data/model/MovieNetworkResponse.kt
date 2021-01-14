package ru.android.study.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieNetworkResponse(
  val adult: Boolean = false,
  val backdrop_path: String = "",
  val genres: List<Genre>,
  val id: Int,
  val overview: String = "",
  val poster_path: String = "",
  val release_date: String,
  val title: String,
  val vote_average: Float,
  val vote_count: Int
)
