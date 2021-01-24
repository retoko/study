package ru.android.study.ui.movies_list.view_models

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.android.study.data.model.Movie
import ru.android.study.data.repositories.MoviesRepository

class MoviesListViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
  private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
  val mutableMoviesList: LiveData<List<Movie>> get() = _mutableMoviesList

  private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Log.e(
      "Movies",
      "MoviesListViewModel",
      throwable
    )
    // TODO: return error from view model and show it.
  }

  fun loadMovies() {
    if (mutableMoviesList.value?.isEmpty() != true) { return }
    viewModelScope.launch(exceptionHandler) {
      val movies = async { moviesRepository.getMovies() }
      _mutableMoviesList.setValue(movies.await())
    }
  }
}
