package ru.android.study.jobs

import androidx.work.*
import java.util.concurrent.TimeUnit

class MoviesWorkScheduler(private val workManager: WorkManager) {

  private val repeatTime: Long = 8
  private val constraints =
    Constraints.Builder()
      .setRequiredNetworkType(NetworkType.CONNECTED)
      .setRequiresCharging(true)
      .build()

  private val constrainedPeriodicRequest =
    PeriodicWorkRequest.Builder(MoviesPreloadWorker::class.java, repeatTime, TimeUnit.HOURS)
      .setConstraints(constraints)
      .build()

  fun startBackgroundMoviesPreload() {
    workManager.enqueue(constrainedPeriodicRequest)
  }
}
