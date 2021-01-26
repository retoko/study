package ru.android.study.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.android.study.data.db.MoviesDbContract

@Entity(tableName = MoviesDbContract.Movies.TABLE_NAME)
data class MovieEntity(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_ID)
  val id: Int,

  @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_TITLE)
  val title: String,

  @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_OVERVIEW)
  val overview: String?,

  @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_POSTER_PATH)
  val poster_path: String?,

  @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_BACKDROP_PATH)
  val backdrop_path: String?,

  @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_RATINGS)
  val ratings: Float,

  @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_NUMBER_OF_RATINGS)
  val numberOfRatings: Int,

  @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_MINIMUM_AGE)
  val adult: Boolean?
)
