package ru.android.study.data.model

data class Movie(
  val id: Int,
  val name: String,
  val genre: String,
  var rating: Double,
  val reviewsCount: Int,
  val ageLimit: String,
  val storyline: String,
  val actorIds: List<Int>,
  val filmDuration: Int,
  val miniature: String,
  val background: String
) {
  companion object {
    val empty = Movie(
      0,
      "",
      "",
      0.0,
      0,
      "0+",
      "",
      listOf(0),
      0,
      "@drawable/ic_no_image",
      "@drawable/ic_no_image"
    )
  }
}
