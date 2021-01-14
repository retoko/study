package ru.android.study.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenresNetworkResponse(val genres: List<Genre>)
