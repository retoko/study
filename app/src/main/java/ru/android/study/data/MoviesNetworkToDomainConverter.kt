package ru.android.study.data

import ru.android.study.data.model.Genre
import ru.android.study.data.model.Movie
import ru.android.study.data.model.MovieNetworkResponse

class MoviesNetworkToDomainConverter {

  fun movieToDomain(movie: MovieNetworkResponse, imageBaseUrl: String, genres: List<Genre>): Movie {
    val ageLimitForKids = 13
    val ageLimitForAdults = 16
    val minimumAge = if (movie.adult == true) ageLimitForAdults else ageLimitForKids
    return Movie(
      movie.id,
      movie.title,
      movie.overview ?: "",
      imageBaseUrl + "original" + movie.poster_path,
      imageBaseUrl + "original" + movie.backdrop_path,
      movie.vote_average,
      movie.vote_count,
      minimumAge,
      genres
    )
  }
}
