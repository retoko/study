package ru.android.study.data.db

import android.provider.BaseColumns

object MoviesDbContract {
  const val DATABASE_NAME = "movies"

  object Movies {
    const val TABLE_NAME = "movies"

    const val COLUMN_NAME_ID = BaseColumns._ID
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_OVERVIEW = "overview"
    const val COLUMN_NAME_POSTER_PATH = "poster_path"
    const val COLUMN_NAME_BACKDROP_PATH = "backdrop_path"
    const val COLUMN_NAME_RATINGS = "ratings"
    const val COLUMN_NAME_NUMBER_OF_RATINGS = "numberOfRatings"
    const val COLUMN_NAME_MINIMUM_AGE = "minimumAge"
  }

  object Actor {
    const val TABLE_NAME = "actors"

    const val COLUMN_NAME_ID = BaseColumns._ID
    const val COLUMN_NAME_MOVIE_ID = "movie_id"
    const val COLUMN_NAME_NAME = "name"
    const val COLUMN_NAME_PROFILE_PATH = "profile_path"
  }

  object Genre {
    const val TABLE_NAME = "genres"

    const val COLUMN_NAME_ID = BaseColumns._ID
    const val COLUMN_NAME_MOVIE_ID = "movie_id"
    const val COLUMN_NAME_NAME = "name"
  }
}
