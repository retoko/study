package ru.android.study.ui.movies_details.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.android.study.data.MoviesService
import ru.android.study.data.model.Movie

class MoviesDetailsViewModel: ViewModel() {
  private val moviesService = MoviesService()
  private val _mutableMovie = MutableLiveData<Movie>()
  val mutableMovie: LiveData<Movie> get() = _mutableMovie

  fun loadMovie(context: Context, id: Int) {
    viewModelScope.launch {
      val movie = async { moviesService.getMovie(context, id) }
      _mutableMovie.setValue(movie.await())
    }
  }
}
