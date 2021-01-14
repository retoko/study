package ru.android.study.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationNetworkResponse(val images: Images)

@Serializable
data class Images(val base_url: String)
