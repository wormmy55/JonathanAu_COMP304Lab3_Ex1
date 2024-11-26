package com.jonathan.jonathanau_comp304lab3_ex1.views

import com.jonathan.jonathanau_comp304lab3_ex1.data.Weather

data class WeatherUIState (
    val isLoading: Boolean = false,
    val weather: List<Weather> = emptyList(),
    val error: String? = null,
)