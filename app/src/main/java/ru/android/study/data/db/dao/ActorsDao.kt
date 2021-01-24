package ru.android.study.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.android.study.data.db.entity.ActorEntity

@Dao
interface ActorsDao {
  @Query("SELECT * FROM actors WHERE actors.movie_id = :movieId ORDER BY actors._id ASC")
  suspend fun getActorsByMovieId(movieId: Int): List<ActorEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(actors: List<ActorEntity>)
}
