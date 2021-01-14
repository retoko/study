package ru.android.study.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreditsNetworkResponse(val cast: List<Cast>)

@Serializable
data class Cast(
  val id: Int,
  val name: String,
  val profile_path: String = ""
)
