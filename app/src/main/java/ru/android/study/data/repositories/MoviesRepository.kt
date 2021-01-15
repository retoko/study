package ru.android.study.data.repositories

import ru.android.study.data.model.Movie
import ru.android.study.data.network.retrofit.MoviesApiClient
import ru.android.study.data.network.retrofit.MoviesApiService
import ru.android.study.data.utils.MoviesNetworkToDomainConverter

class MoviesRepository(private val moviesApiService: MoviesApiService) {
  private val moviesToDomainConverter = MoviesNetworkToDomainConverter()

  suspend fun getMovies(): List<Movie> {
    val imageBaseUrl = moviesApiService.getConfig().images.base_url
    val movies = moviesApiService.getMovies().results
    val genres = moviesApiService.getGenres().genres

    return movies.map { movie ->
      val movieGenres = genres.filter { movie.genre_ids!!.contains(it.id) }
      moviesToDomainConverter.movieToDomain(movie, imageBaseUrl, movieGenres)
    }
  }

  suspend fun getMovie(id: Int): Movie {
    val imageBaseUrl = MoviesApiClient.moviesApiClient.getConfig().images.base_url
    val movie = moviesApiService.getMovie(id)

    return moviesToDomainConverter.movieToDomain(movie, imageBaseUrl, movie.genres!!)
  }
}
