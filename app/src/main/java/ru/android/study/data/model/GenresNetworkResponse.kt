package ru.android.study.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class GenresNetworkResponse(val genres: List<Genre>)
