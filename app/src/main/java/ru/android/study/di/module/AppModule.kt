package ru.android.study.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.android.study.MoviesApplication
import javax.inject.Singleton

@Module
class AppModule(
  private val application: MoviesApplication
) {

  @Provides
  @Singleton
  fun providesApplication(): Application = application

  @Provides
  @Singleton
  fun providesCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
