package ru.android.study.data

import android.content.Context
import ru.android.study.data.model.Movie
import ru.android.study.data.model.loadMovies

class MoviesService {
  suspend fun getMovies(context: Context): List<Movie> {
    return loadMovies(context)
  }

  suspend fun getMovie(context: Context, id: Int): Movie? {
    return getMovies(context).find { it.id == id }
  }
}
