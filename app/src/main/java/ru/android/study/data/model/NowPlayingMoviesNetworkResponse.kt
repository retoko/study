package ru.android.study.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingMoviesNetworkResponse(
  val page: Int,
  val results: List<Results>,
  val total_pages: Int,
  val total_results: Int
)

@Serializable
data class Results(
  val poster_path: String = "",
  val adult: Boolean,
  val overview: String = "",
  val genre_ids: List<Int>,
  val id: Int,
  val title: String,
  val backdrop_path: String = "",
  val vote_count: Int,
  val vote_average: Float
)
