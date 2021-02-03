package ru.android.study.di.module

import dagger.Module
import dagger.Provides
import ru.android.study.data.MoviesDataConverter
import ru.android.study.data.db.dao.ActorsDao
import ru.android.study.data.db.dao.MoviesDao
import ru.android.study.data.network.retrofit.MoviesApiService
import ru.android.study.data.repositories.ActorsRepository
import ru.android.study.data.repositories.MoviesRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {

  @Singleton
  @Provides
  fun providesMoviesRepository(
    moviesApiService: MoviesApiService,
    moviesToDomainConverter: MoviesDataConverter,
    moviesDao: MoviesDao
  ): MoviesRepository {
    return MoviesRepository(moviesApiService, moviesToDomainConverter, moviesDao)
  }

  @Singleton
  @Provides
  fun providesActorsRepository(
    moviesApiService: MoviesApiService,
    actorsDao: ActorsDao
  ): ActorsRepository {
    return ActorsRepository(moviesApiService, actorsDao)
  }
}
