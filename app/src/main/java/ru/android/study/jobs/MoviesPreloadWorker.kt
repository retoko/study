package ru.android.study.jobs

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ru.android.study.MoviesApplication
import ru.android.study.data.repositories.ActorsRepository
import ru.android.study.data.repositories.MoviesRepository
import javax.inject.Inject

class MoviesPreloadWorker(
  context: Context,
  params: WorkerParameters
): CoroutineWorker(context, params) {

  @Inject
  lateinit var moviesRepository: MoviesRepository

  @Inject
  lateinit var actorsRepository: ActorsRepository

  override suspend fun doWork(): Result {
    MoviesApplication.hasAndroidInjector.androidInjector().inject(this)

    return try {
      val movies = moviesRepository.getMovies(true)
      movies.forEach { actorsRepository.getActors(it.id, true) }
      Result.success()
    } catch (e: Exception) {
      Result.failure()
    }
  }
}
