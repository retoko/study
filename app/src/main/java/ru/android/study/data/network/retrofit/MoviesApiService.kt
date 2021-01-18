package ru.android.study.data.network.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.android.study.BuildConfig
import ru.android.study.data.model.*

interface MoviesApiService {
  @GET("genre/movie/list")
  suspend fun getGenres(
    @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
  ): GenresNetworkResponse

  @GET("configuration")
  suspend fun getConfig(
    @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
  ): ConfigurationNetworkResponse

  @GET("movie/{movie_id}")
  suspend fun getMovie(
    @Path("movie_id") id: Int?,
    @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
  ): MovieNetworkResponse

  @GET("movie/now_playing")
  suspend fun getMovies(
    @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    @Query("page") page: Int = 1
  ): NowPlayingMoviesNetworkResponse

  @GET("movie/{movie_id}/credits")
  suspend fun getActors(
    @Path("movie_id") id: Int?,
    @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
  ): CreditsNetworkResponse
}
