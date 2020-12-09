package ru.android.study.data

import ru.android.study.data.model.Actor
import ru.android.study.R

class ActorsService {
  fun getActors(): List<Actor> {
    return listOf(
      Actor(1, 1, "Robert Downey Jr.", R.drawable.robert_dj),
      Actor(2, 1, "Chris Evans", R.drawable.chris_evans),
      Actor(3, 1, "Mark Ruffalo", R.drawable.mark_ruffalo),
      Actor(4, 1, "Chris Hemsworth", R.drawable.chris_hemsworth)
    )
  }

  fun getActorsForMovie(movieId: Int): List<Actor> {
    return getActors().filter { it.movieId == movieId }
  }
}
