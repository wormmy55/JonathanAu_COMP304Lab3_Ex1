package com.jonathan.jonathanau_comp304lab3_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.AppNavigationContent
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.ContentType
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.DeviceFoldPosture
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.NavigationType
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.WeatherRoutes
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.isBookPosture
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.isSeparating
import com.jonathan.jonathanau_comp304lab3_ex1.ui.theme.JonathanAu_COMP304Lab3_Ex1Theme
import com.jonathan.jonathanau_comp304lab3_ex1.views.WeatherNavigationDrawer
import com.jonathan.jonathanau_comp304lab3_ex1.workers.WeatherSyncWorker
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

var globalIndex = 0
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startWeatherSync()
        val deviceFoldingPostureFlow = WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
            .flowWithLifecycle(this.lifecycle)
            .map { layoutInfo ->
                val foldingFeature =
                    layoutInfo.displayFeatures
                        .filterIsInstance<FoldingFeature>()
                        .firstOrNull()
                when {
                    isBookPosture(foldingFeature) ->
                        DeviceFoldPosture.BookPosture(foldingFeature.bounds)
                    isSeparating(foldingFeature) ->
                        DeviceFoldPosture.SeparatingPosture(
                            foldingFeature.bounds,
                            foldingFeature.orientation
                        )
                    else -> DeviceFoldPosture.NormalPosture
                }
            }
            .stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = DeviceFoldPosture.NormalPosture
            )
        enableEdgeToEdge()
        setContent {
            val devicePosture = deviceFoldingPostureFlow.collectAsStateWithLifecycle().value
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val navController = rememberNavController()

            JonathanAu_COMP304Lab3_Ex1Theme {
                val navigationType: NavigationType
                val contentType: ContentType
                when (windowSizeClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> {
                        navigationType = NavigationType.BottomNavigation
                        contentType = ContentType.List
                    }

                    WindowWidthSizeClass.Medium -> {
                        navigationType = NavigationType.NavigationRail
                        contentType = if (devicePosture is DeviceFoldPosture.BookPosture
                            || devicePosture is DeviceFoldPosture.SeparatingPosture
                            ) {
                            ContentType.ListAndDetail
                        } else {
                            ContentType.List

                        }
                    }
                    WindowWidthSizeClass.Expanded -> {
                        navigationType = if (devicePosture is DeviceFoldPosture.BookPosture) {
                            NavigationType.NavigationRail
                        } else {
                            NavigationType.NavigationDrawer
                        }
                        contentType = ContentType.ListAndDetail
                    }
                    else -> {
                        navigationType = NavigationType.BottomNavigation
                        contentType = ContentType.List
                    }
                }
                if (navigationType == NavigationType.NavigationDrawer) {
                    PermanentNavigationDrawer(
                        drawerContent = {
                            PermanentDrawerSheet {
                                WeatherNavigationDrawer(
                                    onFavoriteClicked = {
                                        navController.navigate(WeatherRoutes.FavoriteScreen.route)
                                    },
                                    onHomeClicked = {
                                        navController.navigate(WeatherRoutes.HomeScreen.route)
                                    },
                                    onDrawerClicked = {
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    }
                                )
                            }
                        }
                    )   {
                        AppNavigationContent(
                            navigationType = navigationType,
                            contentType = contentType,
                            navController = navController,
                            onFavoriteClicked = {
                                navController.navigate(WeatherRoutes.FavoriteScreen.route)
                            },
                            onHomeClicked = {
                                navController.navigate(WeatherRoutes.HomeScreen.route)
                            },
                            onDrawerClicked = {
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    }
                } else {
                    ModalNavigationDrawer(
                        drawerContent = {
                            ModalDrawerSheet {
                                WeatherNavigationDrawer(
                                    onFavoriteClicked = {
                                        navController.navigate(WeatherRoutes.FavoriteScreen.route)
                                    },
                                    onHomeClicked = {
                                        navController.navigate(WeatherRoutes.HomeScreen.route)
                                    },
                                    onDrawerClicked = {
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    }
                                )
                            }
                        },
                        drawerState = drawerState
                    ) {
                        AppNavigationContent(
                            navigationType = navigationType,
                            contentType = contentType,
                            navController = navController,
                            onFavoriteClicked = {
                                navController.navigate(WeatherRoutes.FavoriteScreen.route)
                            },
                            onHomeClicked = {
                                navController.navigate(WeatherRoutes.HomeScreen.route)},
                            onDrawerClicked = {
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
/*
@Composable
fun MainScaffold(
    //windowSizeClass : WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
){
    //val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    //val showTopAppBar = windowSizeClass.windowHeightSizeClass != WindowHeightSizeClass.COMPACT
    Scaffold{innerPadding ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = WeatherRoutes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            //this Route leeds to the Home Screen
            composable(WeatherRoutes.Home.route){
                HomeView(
                    //This is where the routes are set
                    onHourlyWeatherButtonClick = { navController.navigate(WeatherRoutes.HourlyForecast.route) },
                    onDailyWeatherButtonClick = { navController.navigate(WeatherRoutes.DailyForecast.route) },
                    onWeeklyWeatherButtonClick = { navController.navigate(WeatherRoutes.WeeklyForecast.route) },
                )
            }
            //This Route leads to the CreateTask Screen
            composable(WeatherRoutes.HourlyForecast.route) {
                HourlyViewScreen(
                    onHomeButtonClick = { navController.navigate(WeatherRoutes.Home.route) },
                    onDailyWeatherButtonClick = { navController.navigate(WeatherRoutes.DailyForecast.route) },
                    onWeeklyWeatherButtonClick = { navController.navigate(WeatherRoutes.WeeklyForecast.route) },
                    listIndex = globalIndex,
                )
            }
            //This Route leeds to the ViewEdit Screen
            composable(WeatherRoutes.DailyForecast.route){
                DailyViewScreen(
                    onHomeButtonClick = { navController.navigate(WeatherRoutes.Home.route) },
                    onHourlyWeatherButtonClick = { navController.navigate(WeatherRoutes.HourlyForecast.route) },
                    onWeeklyWeatherButtonClick = { navController.navigate(WeatherRoutes.WeeklyForecast.route) },
                    //This sets the listIndex to the global index
                    // more about this in ViewEditTasks.kt
                    listIndex = globalIndex,
                )
            }
            //This Route leads to the CreateTask Screen
            composable(WeatherRoutes.WeeklyForecast.route) {
                WeeklyViewScreen(
                    onHomeButtonClick = { navController.navigate(WeatherRoutes.Home.route) },
                    onHourlyWeatherButtonClick = { navController.navigate(WeatherRoutes.HourlyForecast.route) },
                    onDailyWeatherButtonClick = { navController.navigate(WeatherRoutes.DailyForecast.route) },
                    listIndex = globalIndex,
                )
            }
        }
    }
}*/

private fun startWeatherSync() {
    val syncWeatherWorkRequest = OneTimeWorkRequestBuilder<WeatherSyncWorker>()
        .setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiredBatteryNotLow(true)
                .build()
        )
        .build()

    WorkManager.getInstance(applicationContext).enqueueUniqueWork(
        "syncWeather",
        ExistingWorkPolicy.KEEP,
        syncWeatherWorkRequest
    )
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JonathanAu_COMP304Lab3_Ex1Theme {
        MainScaffold()
    }
}*/