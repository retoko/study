package ru.android.study.data.db

import androidx.room.Database
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
}
