package ru.android.study.ui.movies_details.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.android.study.data.model.Actor
import ru.android.study.data.model.Movie
import ru.android.study.data.repositories.ActorsRepository
import ru.android.study.data.repositories.MoviesRepository

class MoviesDetailsViewModel(
  private val moviesService: MoviesRepository,
  private val actorsRepository: ActorsRepository
): ViewModel() {
  private val _mutableMovie = MutableLiveData<Movie>()
  private val _mutableActors = MutableLiveData<List<Actor>>()
  val mutableMovie: LiveData<Movie> get() = _mutableMovie
  val mutableActors: LiveData<List<Actor>> get() = _mutableActors

  private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Log.e(
      "Movies",
      "MoviesDetailsViewModel",
      throwable
    )
    // TODO: return error from view model and show it.
  }

  fun loadMovie(id: Int) {
    mutableMovie.value?.let { return }
    viewModelScope.launch(exceptionHandler) {
      val deferredMovie = async { moviesService.getMovie(id) }
      val deferredMovieActors = async { actorsRepository.getActors(id) }
      _mutableMovie.value = deferredMovie.await()
      _mutableActors.value = deferredMovieActors.await()
    }
  }
}
