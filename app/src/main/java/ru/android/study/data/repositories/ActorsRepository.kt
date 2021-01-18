package ru.android.study.data.repositories

import ru.android.study.data.model.Actor
import ru.android.study.data.network.retrofit.MoviesApiService

class ActorsRepository(private val moviesApiService: MoviesApiService) {

  suspend fun getActors(movieId: Int): List<Actor> {
    val imageBaseUrl = moviesApiService.getConfig().images.base_url
    val actorsResponse = moviesApiService.getActors(movieId).cast

    return actorsResponse.map { actor ->
      Actor(actor.id, actor.name,imageBaseUrl + "original" + actor.profile_path)
    }
  }
}