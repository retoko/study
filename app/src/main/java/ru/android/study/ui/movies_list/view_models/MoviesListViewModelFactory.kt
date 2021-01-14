package ru.android.study.ui.movies_list.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.android.study.data.network.MoviesDataSource

class MoviesListViewModelFactory(private val moviesService: MoviesDataSource): ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
    MoviesListViewModel::class.java -> MoviesListViewModel(moviesService)
    else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
  } as T
}
