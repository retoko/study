package ru.android.study.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.android.study.jobs.MoviesPreloadWorker

@Module
abstract class WorkerModule {

  @ContributesAndroidInjector
  abstract fun injectMoviesPreloadWorker(): MoviesPreloadWorker
}
