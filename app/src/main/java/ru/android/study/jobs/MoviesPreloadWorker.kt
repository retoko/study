package ru.android.study.jobs

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ru.android.study.data.MoviesDataConverter
import ru.android.study.data.db.MoviesDataBase
import ru.android.study.data.network.retrofit.MoviesApiClient
import ru.android.study.data.network.retrofit.MoviesApiService
import ru.android.study.data.repositories.ActorsRepository
import ru.android.study.data.repositories.MoviesRepository

class MyWorker(context: Context, params: WorkerParameters): CoroutineWorker(context, params) {
  private val ctx: Context = context
  private lateinit var database: MoviesDataBase
  private lateinit var moviesApiClint: MoviesApiService
  private lateinit var moviesDataConverter: MoviesDataConverter
  private lateinit var moviesRepository: MoviesRepository
  private lateinit var actorsRepository: ActorsRepository

  override suspend fun doWork(): Result {
    database = MoviesDataBase.create(ctx)
    moviesApiClint = MoviesApiClient.moviesApiClient
    moviesDataConverter = MoviesDataConverter()
    actorsRepository = ActorsRepository(moviesApiClint, database.actorsDao)
    moviesRepository = MoviesRepository(
      moviesApiClint,
      moviesDataConverter,
      database.moviesDao
    )

    return try {
      val movies = moviesRepository.getMovies(true)
      movies.forEach { actorsRepository.getActors(it.id, true) }

      Result.success()
    } catch (e: Exception) {
      Result.failure()
    }
  }
}
