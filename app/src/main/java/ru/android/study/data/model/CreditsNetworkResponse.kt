package ru.android.study.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class CreditsNetworkResponse(val cast: List<Cast>)

@Keep
@Serializable
data class Cast(
  val id: Int,
  val name: String,
  val profile_path: String?
)
