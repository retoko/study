package ru.android.study.data

import ru.android.study.data.model.Actor

class ActorService {
  fun getActors(): List<Actor> {
    return listOf(
      Actor(1, 0, "", "@drawable/ic_no_image"),
      Actor(2, 0, "", "@drawable/ic_no_image"),
      Actor(3, 0, "", "@drawable/ic_no_image"),
      Actor(4, 0, "", "@drawable/ic_no_image")
    )
  }
}