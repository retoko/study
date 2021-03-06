package ru.android.study.data.repositories

import ru.android.study.data.model.Movie
import ru.android.study.data.network.retrofit.MoviesApiService
import ru.android.study.data.MoviesDataConverter
import ru.android.study.data.db.dao.MoviesDao
import javax.inject.Inject

class MoviesRepository
@Inject constructor(
  private val moviesApiService: MoviesApiService,
  private val moviesToDomainConverter: MoviesDataConverter,
  private val moviesDao: MoviesDao
) {
  suspend fun getMovies(refresh: Boolean = false): List<Movie> {
    if (!refresh) {
      val moviesFromDb = getMoviesFromDb()

      if (moviesFromDb.isNotEmpty()) { return moviesFromDb }
    }

    val imageBaseUrl = moviesApiService.getConfig().images.base_url
    val movies = moviesApiService.getMovies().results
    val movieGenresEntityList = movies.map { movie ->
      val movieGenreIds = movie.genre_ids ?: emptyList()
      val genresResponse = moviesApiService.getGenres().genres
      val genres = genresResponse.filter { movieGenreIds.contains(it.id) }
      moviesToDomainConverter.movieNetworkToEntity(movie, imageBaseUrl, genres)
    }
    val moviesEntityList = movieGenresEntityList.map { it.movie }
    val genresEntityList = movieGenresEntityList.map { it.genres }

    moviesDao.insertMoviesAndGenres(moviesEntityList, genresEntityList.flatten())

    return movies.map { movie ->
      val movieGenreIds = movie.genre_ids ?: emptyList()
      val genresResponse = moviesApiService.getGenres().genres
      val genres = genresResponse.filter { movieGenreIds.contains(it.id) }
      moviesToDomainConverter.movieNetworkToDomain(movie, imageBaseUrl, genres)
    }
  }

  suspend fun getMovie(movieId: Int): Movie {
    val movieFromDb = getMovieFromDb(movieId)

    if (movieFromDb != null) { return movieFromDb }

    val imageBaseUrl = moviesApiService.getConfig().images.base_url
    val movie = moviesApiService.getMovie(movieId)
    val genreIds = movie.genres?.map { it.id } ?: emptyList()
    val genresResponse = moviesApiService.getGenres().genres
    val genres = genresResponse.filter { genreIds.contains(it.id) }
    val movieGenresEntity = moviesToDomainConverter.movieNetworkToEntity(movie, imageBaseUrl, genres)

    moviesDao.insertMovieGenres(movieGenresEntity)

    return moviesToDomainConverter.movieNetworkToDomain(movie, imageBaseUrl, genres)
  }

  private suspend fun getMoviesFromDb(): List<Movie> {
    val movies = moviesDao.getAll()

    return movies.map { movie ->
      moviesToDomainConverter.movieEntityToDomain(movie)
    }
  }

  private suspend fun getMovieFromDb(movieId: Int): Movie? {
    val movie = moviesDao.getMovieById(movieId)

    movie ?: return null

    return moviesToDomainConverter.movieEntityToDomain(movie)
  }
}
