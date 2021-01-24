package ru.android.study.data

import ru.android.study.data.db.entity.GenreEntity
import ru.android.study.data.db.entity.MovieEntity
import ru.android.study.data.db.entity.MovieGenresEntity
import ru.android.study.data.model.Genre
import ru.android.study.data.model.Movie
import ru.android.study.data.model.MovieNetworkResponse

class MoviesDataConverter {

  fun movieNetworkToDomain(
    movie: MovieNetworkResponse,
    imageBaseUrl: String,
    genres: List<Genre>
  ): Movie {
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

  fun movieNetworkToEntity(
    movie: MovieNetworkResponse,
    imageBaseUrl: String,
    genres: List<Genre>
  ): MovieGenresEntity {
    val genreEntityList = genres.map { genre ->
      GenreEntity(
        genre.id,
        movie.id,
        genre.name
      )
    }
    val movieEntity = MovieEntity(
      movie.id,
      movie.title,
      movie.overview,
      imageBaseUrl + "original" + movie.poster_path,
      imageBaseUrl + "original" + movie.backdrop_path,
      movie.vote_average,
      movie.vote_count,
      movie.adult
    )
    return MovieGenresEntity(
      movieEntity,
      genreEntityList
    )
  }

  fun movieEntityToDomain(
    movieWithGenre: MovieGenresEntity
  ): Movie {
    val ageLimitForKids = 13
    val ageLimitForAdults = 16
    val minimumAge = if (movieWithGenre.movie.adult == true) ageLimitForAdults else ageLimitForKids
    val genres = movieWithGenre.genres.map { genre -> Genre(genre.id, genre.name) }
    return Movie(
      movieWithGenre.movie.id,
      movieWithGenre.movie.title,
      movieWithGenre.movie.overview ?: "",
      movieWithGenre.movie.poster_path ?: "",
      movieWithGenre.movie.backdrop_path ?: "",
      movieWithGenre.movie.ratings,
      movieWithGenre.movie.numberOfRatings,
      minimumAge,
      genres
    )
  }
}
