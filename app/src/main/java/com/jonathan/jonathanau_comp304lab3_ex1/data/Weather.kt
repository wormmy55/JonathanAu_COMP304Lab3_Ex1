package com.jonathan.jonathanau_comp304lab3_ex1.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather (
    @SerialName("Location")
    val location: String = "",
    @SerialName("Temperature")
    val temperature: String = "",
    @SerialName("Description")
    val description: String = "",
    @SerialName("Longitude")
    val longitude: Double = -79.3832,
    @SerialName("Latitude")
    val latitude: Double = 43.6532,
    val isFavorite: Boolean = false
)

