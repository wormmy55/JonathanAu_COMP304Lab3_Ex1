package com.jonathan.jonathanau_comp304lab3_ex1.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jonathan.jonathanau_comp304lab3_ex1.data.WeatherRepository

class WeatherSyncWorker (
    appContext: Context,
    workerParams: WorkerParameters,
    private val weatherRepository: WeatherRepository
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            weatherRepository.fetchRemoteWeather()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}