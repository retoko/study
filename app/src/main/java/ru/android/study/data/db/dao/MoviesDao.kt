package ru.android.study.data.db.dao

import androidx.room.*
import ru.android.study.data.db.entity.GenreEntity
import ru.android.study.data.db.entity.MovieEntity
import ru.android.study.data.db.entity.MovieGenresEntity

@Dao
interface MoviesDao {
  @Transaction
  @Query("SELECT * FROM movies ORDER BY movies._id ASC")
  suspend fun getAll(): List<MovieGenresEntity>

  @Transaction
  @Query("SELECT * FROM movies WHERE movies._id = :movieId ORDER BY movies._id ASC LIMIT 1")
  suspend fun getMovieById(movieId: Int): MovieGenresEntity?

  @Transaction
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(movie: MovieEntity)

  @Transaction
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMovies(movies: List<MovieEntity>)

  @Transaction
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertGenres(genres: List<GenreEntity>)
}
