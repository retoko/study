package ru.android.study.jobs

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit

class MoviesWorkRepository {

  private val constraints =
    Constraints.Builder()
      .setRequiredNetworkType(NetworkType.CONNECTED)
      .setRequiresCharging(true)
      .build()

  val constrainedPeriodicRequest =
    PeriodicWorkRequest.Builder(MyWorker::class.java, 8, TimeUnit.HOURS)
      .setConstraints(constraints)
      .build()
}
