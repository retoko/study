package ru.android.study.data.db.dao

import androidx.room.*
import ru.android.study.data.db.MoviesDbContract
import ru.android.study.data.db.entity.GenreEntity
import ru.android.study.data.db.entity.MovieEntity
import ru.android.study.data.db.entity.MovieGenresEntity

@Dao
interface MoviesDao {
  @Transaction
  @Query(
    "SELECT * FROM ${MoviesDbContract.Movies.TABLE_NAME} " +
    "ORDER BY ${MoviesDbContract.Movies.TABLE_NAME}.${MoviesDbContract.Movies.COLUMN_NAME_ID} ASC"
  )
  suspend fun getAll(): List<MovieGenresEntity>

  @Transaction
  @Query(
    "SELECT * FROM ${MoviesDbContract.Movies.TABLE_NAME} " +
    "WHERE ${MoviesDbContract.Movies.TABLE_NAME}.${MoviesDbContract.Movies.COLUMN_NAME_ID} = :movieId " +
    "ORDER BY ${MoviesDbContract.Movies.TABLE_NAME}.${MoviesDbContract.Movies.COLUMN_NAME_ID} ASC LIMIT 1"
  )
  suspend fun getMovieById(movieId: Int): MovieGenresEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(movie: MovieEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMovies(movies: List<MovieEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertGenres(genres: List<GenreEntity>)

  @Transaction
  suspend fun insertMovieGenres(movieGenresEntity: MovieGenresEntity) {
    insert(movieGenresEntity.movie)
    insertGenres(movieGenresEntity.genres)
  }

  @Transaction
  suspend fun insertMoviesAndGenres(movies: List<MovieEntity>, genres: List<GenreEntity>) {
    insertMovies(movies)
    insertGenres(genres)
  }
}
