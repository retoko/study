package ru.android.study.ui.movies_details.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.android.study.data.network.MoviesDataSource

class MoviesDetailsViewModelFactory(private val moviesService: MoviesDataSource): ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
    MoviesDetailsViewModel::class.java -> MoviesDetailsViewModel(moviesService)
    else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
  } as T
}
