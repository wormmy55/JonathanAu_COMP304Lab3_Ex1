package com.jonathan.jonathanau_comp304lab3_ex1.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.jonathan.jonathanau_comp304lab3_ex1.data.Weather
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun WeatherList(
    onWeatherClicked: (Weather) -> Unit,
    weather: List<Weather>,
    modifier: Modifier,
    onFavoriteClicked: (Weather) -> Unit
){
    LazyColumn (
        modifier = modifier
    ) {
        items(weather){ weather ->
            WeatherListItem(
                weather = weather,
                onWeatherClicked = onWeatherClicked,
                onFavoriteClicked = onFavoriteClicked
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WeatherListItem(
    weather: Weather,
    onWeatherClicked: (Weather) -> Unit,
    onFavoriteClicked: (Weather) -> Unit
){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clickable { onWeatherClicked(weather) }
        ){
            /*AsyncImage(
                model = "",
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )*/

            Row(
                modifier = Modifier
                    .padding(start = 6.dp, end = 6.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                FlowRow(
                    modifier = Modifier
                        .padding(start = 6.dp, end = 6.dp)
                ){
                    /*repeat(weather.location){
                        SuggestionChip(
                            modifier = Modifier
                                .padding(start = 3.dp, end = 3.dp),
                            onClick = { },
                            label = {
                                Text(text = weather.location[it])
                            }
                        )
                    }*/
                }
                Icon(
                    modifier = Modifier
                        .clickable{
                            onFavoriteClicked(weather.copy(isFavorite = !weather.isFavorite))
                        },
                    imageVector = if (weather.isFavorite){
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = "Favorite",
                    tint = if (weather.isFavorite) {
                        Color.Red
                    } else {
                        Color.Gray
                    }
                )
            }
        }
    }
}