package ru.android.study.di.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.android.study.data.network.retrofit.MoviesApiService
import javax.inject.Singleton

@Module
object NetworkModule {

  private const val BASE_URL = "https://api.themoviedb.org/3/"
  private val client = OkHttpClient().newBuilder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()
  private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
  }

  @Provides
  @Singleton
  fun providesRetrofitInstance(): Retrofit {
    return Retrofit.Builder()
      .client(client)
      .baseUrl(BASE_URL)
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .build()
  }

  @Provides
  @Singleton
  fun provideMoviesApi(
    retrofit: Retrofit
  ): MoviesApiService = retrofit.create(MoviesApiService::class.java)
}
