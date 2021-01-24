package ru.android.study.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.android.study.data.db.MoviesDbContract

@Entity(tableName = MoviesDbContract.Actor.TABLE_NAME)
data class ActorEntity(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = MoviesDbContract.Actor.COLUMN_NAME_ID)
  val id: Int,

  @ColumnInfo(name = MoviesDbContract.Actor.COLUMN_NAME_MOVIE_ID)
  val movie_id: Int,

  @ColumnInfo(name = MoviesDbContract.Actor.COLUMN_NAME_NAME)
  val name: String,

  @ColumnInfo(name = MoviesDbContract.Actor.COLUMN_NAME_PROFILE_PATH)
  val profile_path: String
)
