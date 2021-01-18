package ru.android.study.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NowPlayingMoviesNetworkResponse(
  val page: Int,
  val results: List<MovieNetworkResponse>,
  val total_pages: Int,
  val total_results: Int
)
