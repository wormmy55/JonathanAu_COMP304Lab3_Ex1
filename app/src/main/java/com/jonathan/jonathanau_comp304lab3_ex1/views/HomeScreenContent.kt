package com.jonathan.jonathanau_comp304lab3_ex1.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jonathan.jonathanau_comp304lab3_ex1.data.Weather
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.ContentType

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    onWeatherClicked: (Weather) -> Unit,
    contentType: ContentType,
    weatherUIState: WeatherUIState,
    onFavoriteClicked: (Weather) -> Unit
){
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AnimatedVisibility(
            visible = weatherUIState.isLoading
        ) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(
            visible = weatherUIState.weather.isNotEmpty()
        ) {
            if (contentType == ContentType.List){
                WeatherList(
                    onWeatherClicked = onWeatherClicked,
                    weather = weatherUIState.weather,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onFavoriteClicked = onFavoriteClicked
                )
            }
        }

        AnimatedVisibility(
            visible = weatherUIState.error != null
        ) {
            Text(text = weatherUIState.error ?: "")
        }
    }
}