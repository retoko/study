package ru.android.study.data.db.entity

import androidx.room.*
import androidx.room.Relation
import ru.android.study.data.db.MoviesDbContract

data class MovieGenresEntity(
  @Embedded
  val movie: MovieEntity,

  @Relation(
    parentColumn = MoviesDbContract.Movies.COLUMN_NAME_ID,
    entityColumn = MoviesDbContract.Genre.COLUMN_NAME_MOVIE_ID
  )
  val genres: List<GenreEntity>
)
