package ru.android.study.di.module

import dagger.Module
import dagger.Provides
import ru.android.study.data.MoviesDataConverter
import javax.inject.Singleton

@Module
class MoviesDataConverterModule {

  @Singleton
  @Provides
  fun providesMoviesDataConverter(): MoviesDataConverter = MoviesDataConverter()
}
