package ru.android.study.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.android.study.data.db.MoviesDbContract

@Entity(tableName = MoviesDbContract.Genre.TABLE_NAME)
data class GenreEntity(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = MoviesDbContract.Genre.COLUMN_NAME_ID)
  val id: Int,

  @ColumnInfo(name = MoviesDbContract.Genre.COLUMN_NAME_MOVIE_ID)
  val movieId: Int,

  @ColumnInfo(name = MoviesDbContract.Genre.COLUMN_NAME_NAME)
  val name: String
)
