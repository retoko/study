package ru.android.study.ui.movies_details.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.android.study.data.repositories.ActorsRepository
import ru.android.study.data.repositories.MoviesRepository

class MoviesDetailsViewModelFactory(
  private val moviesService: MoviesRepository,
  private val actorsRepository: ActorsRepository
): ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
    MoviesDetailsViewModel::class.java -> MoviesDetailsViewModel(moviesService, actorsRepository)
    else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
  } as T
}
