package ru.android.study.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.android.study.data.db.MoviesDbContract
import ru.android.study.data.db.entity.ActorEntity

@Dao
interface ActorsDao {
  @Query(
    "SELECT * FROM ${MoviesDbContract.Actor.TABLE_NAME} " +
    "WHERE ${MoviesDbContract.Actor.TABLE_NAME}.${MoviesDbContract.Actor.COLUMN_NAME_MOVIE_ID} = :movieId " +
    "ORDER BY ${MoviesDbContract.Actor.TABLE_NAME}.${MoviesDbContract.Actor.COLUMN_NAME_ID} ASC"
  )
  suspend fun getActorsByMovieId(movieId: Int): List<ActorEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(actors: List<ActorEntity>)
}
