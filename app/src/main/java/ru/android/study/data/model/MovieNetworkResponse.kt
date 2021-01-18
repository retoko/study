package ru.android.study.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class MovieNetworkResponse(
  val adult: Boolean?,
  val backdrop_path: String?,
  val genres: List<Genre>? = null,
  val genre_ids: List<Int>? = null,
  val id: Int,
  val overview: String?,
  val poster_path: String?,
  val release_date: String,
  val title: String,
  val vote_average: Float,
  val vote_count: Int
)
