package ru.android.study.data.network

import ru.android.study.data.model.Actor
import ru.android.study.data.model.Movie
import ru.android.study.data.network.retrofit.MoviesApiClient

class MoviesDataSource {
  suspend fun getMovies(): List<Movie> {
    val imageBaseUrl = MoviesApiClient.moviesApiClient.getConfig().images.base_url
    val movies = MoviesApiClient.moviesApiClient.getMovies().results
    val genres = MoviesApiClient.moviesApiClient.getGenres().genres

    return movies.map { movie ->
      val minimumAge = if (movie.adult) 16 else 13
      val movieGenres = genres.filter { movie.genre_ids.contains(it) }

      Movie(
        movie.id,
        movie.title,
        movie.overview,
        imageBaseUrl + "original" + movie.poster_path,
        imageBaseUrl + "original" + movie.backdrop_path,
        movie.vote_average,
        movie.vote_count,
        minimumAge,
        movieGenres
      )
    }
  }

  suspend fun getMovie(id: Int): Movie {
    val imageBaseUrl = MoviesApiClient.moviesApiClient.getConfig().images.base_url
    val movie = MoviesApiClient.moviesApiClient.getMovie(id)
    val minimumAge = if (movie.adult) 16 else 13

    return Movie(
      movie.id,
      movie.title,
      movie.overview,
      imageBaseUrl + "original" + movie.poster_path,
      imageBaseUrl + "original" + movie.backdrop_path,
      movie.vote_average,
      movie.vote_count,
      minimumAge,
      movie.genres
    )
  }

  suspend fun getActors(movieId: Int): List<Actor> {
    val imageBaseUrl = MoviesApiClient.moviesApiClient.getConfig().images.base_url
    val actorsResponse = MoviesApiClient.moviesApiClient.getActors(movieId).cast

    return actorsResponse.map { actor ->
      Actor(actor.id, actor.name,imageBaseUrl + "original" + actor.profile_path)
    }
  }
}
