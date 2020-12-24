package ru.android.study.ui.movies_details.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.android.study.data.MoviesService
import ru.android.study.data.model.Actor
import ru.android.study.data.model.Movie

class MoviesDetailsViewModel(private val moviesService: MoviesService): ViewModel() {
  private val _mutableMovie = MutableLiveData<Movie>()
  private val _mutableActors = MutableLiveData<List<Actor>>()
  val mutableMovie: LiveData<Movie> get() = _mutableMovie
  val mutableActors: LiveData<List<Actor>> get() = _mutableActors

  fun loadMovie(context: Context, id: Int) {
    mutableMovie.value?.let { return }
    viewModelScope.launch {
      val deferredMovie = async { moviesService.getMovie(context, id) }
      _mutableMovie.value = deferredMovie.await()
      _mutableActors.value = mutableMovie.value?.actors
    }
  }
}
