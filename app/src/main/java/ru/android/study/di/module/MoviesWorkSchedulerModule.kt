package ru.android.study.di.module

import android.app.Application
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import ru.android.study.jobs.MoviesWorkScheduler
import javax.inject.Singleton

@Module
object MoviesWorkSchedulerModule {

  @Singleton
  @Provides
  fun providesMoviesWorkScheduler(
    workManager: WorkManager
  ): MoviesWorkScheduler = MoviesWorkScheduler(workManager)

  @Singleton
  @Provides
  fun providesWorkManager(
    application: Application
  ): WorkManager = WorkManager.getInstance(application)
}
