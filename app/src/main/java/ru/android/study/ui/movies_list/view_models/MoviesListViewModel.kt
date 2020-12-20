package ru.android.study.ui.movies_list.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.android.study.data.MoviesService
import ru.android.study.data.model.Movie

class MoviesListViewModel: ViewModel() {
  private val moviesService = MoviesService()
  private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
  val mutableMoviesList: LiveData<List<Movie>> get() = _mutableMoviesList

  fun loadMovies(context: Context) {
    viewModelScope.launch {
      val movies = async { moviesService.getMovies(context) }
      _mutableMoviesList.setValue(movies.await())
    }
  }
}
