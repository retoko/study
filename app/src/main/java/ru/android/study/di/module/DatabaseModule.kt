package ru.android.study.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.android.study.data.db.MoviesDataBase
import ru.android.study.data.db.MoviesDbContract
import ru.android.study.data.db.dao.ActorsDao
import ru.android.study.data.db.dao.MoviesDao
import javax.inject.Singleton

@Module
object DatabaseModule {

  private const val databaseName = MoviesDbContract.DATABASE_NAME

  @Singleton
  @Provides
  fun providesAppDatabase(
    application: Application
  ): MoviesDataBase = Room
    .databaseBuilder(
      application.applicationContext,
      MoviesDataBase::class.java,
      databaseName
    )
    .fallbackToDestructiveMigration()
    .build()

  @Singleton
  @Provides
  fun providesMoviesDao(
    database: MoviesDataBase
  ): MoviesDao = database.moviesDao

  @Singleton
  @Provides
  fun providesActorsDao(
    database: MoviesDataBase
  ): ActorsDao = database.actorsDao
}
