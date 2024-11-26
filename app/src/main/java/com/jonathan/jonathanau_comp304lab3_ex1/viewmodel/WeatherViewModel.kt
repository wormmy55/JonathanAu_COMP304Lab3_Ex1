package com.jonathan.jonathanau_comp304lab3_ex1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.jonathanau_comp304lab3_ex1.data.NetworkResult
import com.jonathan.jonathanau_comp304lab3_ex1.data.Weather
import com.jonathan.jonathanau_comp304lab3_ex1.data.WeatherRepository
import com.jonathan.jonathanau_comp304lab3_ex1.data.asResult
import com.jonathan.jonathanau_comp304lab3_ex1.views.WeatherUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel (
    private val weatherRepository: WeatherRepository,
): ViewModel(){
    val weatherUIState = MutableStateFlow(WeatherUIState())
    private val _favoriteWeather = MutableStateFlow<List<Weather>>(emptyList())
    val favoriteWeather: StateFlow<List<Weather>> = _favoriteWeather

    init {
        getWeather()
    }

    private fun getWeather(){
        weatherUIState.value = WeatherUIState(isLoading = true)
        viewModelScope.launch {
            weatherRepository.getWeather().asResult().collect { result ->
                when(result) {
                    is NetworkResult.Success -> {
                        weatherUIState.update {
                            it.copy(
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        weatherUIState.update {
                            it.copy(
                                isLoading = true,
                                error = result.error
                            )
                        }
                    }
                }
            }
        }
    }
    fun updateWeather(weather: Weather){
        viewModelScope.launch {
            weatherRepository.updateWeather(weather)
        }
    }
    fun getFavoriteWeather(){
        viewModelScope.launch {
            weatherRepository.getFavoriteWeather().collect{
                _favoriteWeather.value = it
            }
        }
    }
    /*
    fun deleteWeather(weather: Weather){
        viewModelScope.launch {
            weatherRepository.deleteWeather(weather)
        }
    }
    fun addWeather(weather: Weather){
        viewModelScope.launch {
            weatherRepository.addWeather(weather)
        }
    }

    fun getWeatherById(id: Int){
        viewModelScope.launch {
            weatherRepository.getWeatherById(id)
        }
    }*/
}