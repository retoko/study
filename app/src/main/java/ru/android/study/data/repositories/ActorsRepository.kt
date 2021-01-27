package ru.android.study.data.repositories

import ru.android.study.data.db.dao.ActorsDao
import ru.android.study.data.db.entity.ActorEntity
import ru.android.study.data.model.Actor
import ru.android.study.data.network.retrofit.MoviesApiService

class ActorsRepository(
  private val moviesApiService: MoviesApiService,
  private val actorsDao: ActorsDao
) {

  suspend fun getActors(movieId: Int, refresh: Boolean = false): List<Actor> {
    if (!refresh) {
      val actorsFromDb = getActorsFromDb(movieId)

      if (actorsFromDb.isNotEmpty()) { return actorsFromDb }
    }

    val imageBaseUrl = moviesApiService.getConfig().images.base_url
    val actorsResponse = moviesApiService.getActors(movieId).cast

    val actorsEntityList = actorsResponse.map { actor ->
      ActorEntity(
        actor.id,
        movieId,
        actor.name,
        imageBaseUrl + "original" + actor.profile_path
      )
    }

    actorsDao.insert(actorsEntityList)

    return actorsResponse.map { actor ->
      Actor(actor.id, actor.name,imageBaseUrl + "original" + actor.profile_path)
    }
  }

  private suspend fun getActorsFromDb(movieId: Int): List<Actor> {
    val actors = actorsDao.getActorsByMovieId(movieId)

    return actors.map { actor ->
      Actor(actor.id, actor.name, actor.profile_path)
    }
  }
}
