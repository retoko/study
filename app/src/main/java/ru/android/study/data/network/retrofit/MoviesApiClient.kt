package ru.android.study.data.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object MoviesApiClient {
  private const val BASE_URL = "https://api.themoviedb.org/3/"
  private val client = OkHttpClient().newBuilder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()
  private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
  }
  private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
      .client(client)
      .baseUrl(BASE_URL)
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .build()
  }
  val moviesApiClient = retrofit.create(MoviesApiService::class.java)
}
