package ru.android.study.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ConfigurationNetworkResponse(val images: Images)

@Keep
@Serializable
data class Images(val base_url: String)
