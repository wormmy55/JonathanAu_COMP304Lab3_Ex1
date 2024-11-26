package com.jonathan.jonathanau_comp304lab3_ex1.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jonathan.jonathanau_comp304lab3_ex1.data.Weather
import com.jonathan.jonathanau_comp304lab3_ex1.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    onWeatherClicked: (Weather) -> Unit
){
    val weatherViewModel: WeatherViewModel = koinViewModel()
    LaunchedEffect(Unit) {
        weatherViewModel.getFavoriteWeather()
    }
    val weather by weatherViewModel.favoriteWeather.collectAsStateWithLifecycle()

    if (weather.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "No Favorite Weather Locations")
        }
    } else {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(weather){weather ->
                WeatherListItem(
                    weather = weather,
                    onWeatherClicked = onWeatherClicked,
                    onFavoriteClicked = {
                        weatherViewModel.updateWeather(it)
                    }
                )
            }
        }
    }
}