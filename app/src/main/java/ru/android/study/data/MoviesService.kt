package ru.android.study.data

import ru.android.study.data.model.Movie
import ru.android.study.R

class MoviesService {
  fun getMovies(): List<Movie> {
    return listOf(
      Movie(
        1,
        "Avengers: End Game",
        false,
        "Action, Adventure, Fantasy",
        4.0f,
        125,
        13,
        "After the devastating events of Avengers:" +
                " Infinity War, the universe is in ruins." +
                " With the help of remaining allies," +
                " the Avengers assemble once more in order to reverse Thanos\'" +
                " actions and restore balance to the universe.",
        listOf(1,2,3,4),
        137,
        R.drawable.avengers_mini,
        R.drawable.avengers
      ),
      Movie(
        2,
        "Tenet",
        true,
        "Action, Sci-Fi, Thriller",
        5.0f,
        98,
        16,
        "",
        listOf(0,0,0,0),
        97,
        R.drawable.tenet_bg,
        R.drawable.avengers
      ),
      Movie(
        3,
        "Black Widow",
        false,
        "Action, Adventure, Sci-Fi",
        4.0f,
        38,
        13,
        "",
        listOf(0,0,0,0),
        102,
        R.drawable.black_widow_bg,
        R.drawable.avengers
      ),
      Movie(
        4,
        "Wonder Woman 1984",
        false,
        "Action, Adventure, Fantasy",
        5.0f,
        74,
        13,
        "",
        listOf(0,0,0,0),
        120,
        R.drawable.wonder_woman_bg,
        R.drawable.avengers
      )
    )
  }

  fun getMovie(id: Int?): Movie {
    return getMovies().first { it.id == id }
  }
}