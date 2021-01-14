package ru.android.study.ui.movies_details.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.android.study.data.model.Actor
import ru.android.study.data.model.Movie
import ru.android.study.data.network.MoviesDataSource

class MoviesDetailsViewModel(private val moviesService: MoviesDataSource): ViewModel() {
  private val _mutableMovie = MutableLiveData<Movie>()
  private val _mutableActors = MutableLiveData<List<Actor>>()
  val mutableMovie: LiveData<Movie> get() = _mutableMovie
  val mutableActors: LiveData<List<Actor>> get() = _mutableActors

  fun loadMovie(id: Int) {
    mutableMovie.value?.let { return }
    viewModelScope.launch {
      val deferredMovie = async { moviesService.getMovie(id) }
      val deferredMovieActors = async { moviesService.getActors(id) }
      _mutableMovie.value = deferredMovie.await()
      _mutableActors.value = deferredMovieActors.await()
    }
  }
}
