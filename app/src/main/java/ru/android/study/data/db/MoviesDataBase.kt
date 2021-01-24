package ru.android.study.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.android.study.data.db.dao.ActorsDao
import ru.android.study.data.db.dao.MoviesDao
import ru.android.study.data.db.entity.ActorEntity
import ru.android.study.data.db.entity.GenreEntity
import ru.android.study.data.db.entity.MovieEntity

@Database(
  entities = [MovieEntity::class, ActorEntity::class, GenreEntity::class],
  version = 1,
  exportSchema = true
)
abstract class MoviesDataBase : RoomDatabase() {

  abstract val moviesDao: MoviesDao
  abstract val actorsDao: ActorsDao

  companion object {
    private const val databaseName = MoviesDbContract.DATABASE_NAME

    fun create(applicationContext: Context): MoviesDataBase = Room.databaseBuilder(
      applicationContext,
      MoviesDataBase::class.java,
      databaseName
    )
      .fallbackToDestructiveMigration()
      .build()
  }
}
