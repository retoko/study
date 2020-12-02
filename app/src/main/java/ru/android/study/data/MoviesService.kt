package ru.android.study.data

import ru.android.study.data.model.Movie

class MoviesService {
  fun getMovies(): List<Movie> {
    return listOf(
      Movie(
        1,
        "Avengers:\nEnd Game",
        "Action, Adventure, Fantasy",
        4.0,
        125,
        "13+",
        "After the devastating events of Avengers:" +
                " Infinity War, the universe is in ruins." +
                " With the help of remaining allies," +
                " the Avengers assemble once more in order to reverse Thanos\\'" +
                " actions and restore balance to the universe.",
        listOf(1,2,3,4),
        137,
        "@drawable/avengers_mini",
        "@drawable/avengers"
      ),
      Movie(
        2,
        "Avengers:\nEnd Game",
        "Action, Adventure, Fantasy",
        4.0,
        125,
        "13+",
        "After the devastating events of Avengers:" +
                " Infinity War, the universe is in ruins." +
                " With the help of remaining allies," +
                " the Avengers assemble once more in order to reverse Thanos\\'" +
                " actions and restore balance to the universe.",
        listOf(1,2,3,4),
        137,
        "@drawable/avengers_mini",
        "@drawable/avengers"
      ),
      Movie(
        3,
        "Avengers:\nEnd Game",
        "Action, Adventure, Fantasy",
        4.0,
        125,
        "13+",
        "After the devastating events of Avengers:" +
                " Infinity War, the universe is in ruins." +
                " With the help of remaining allies," +
                " the Avengers assemble once more in order to reverse Thanos\\'" +
                " actions and restore balance to the universe.",
        listOf(1,2,3,4),
        137,
        "@drawable/avengers_mini",
        "@drawable/avengers"
      ),
      Movie(
        4,
        "Avengers:\nEnd Game",
        "Action, Adventure, Fantasy",
        4.0,
        125,
        "13+",
        "After the devastating events of Avengers:" +
                " Infinity War, the universe is in ruins." +
                " With the help of remaining allies," +
                " the Avengers assemble once more in order to reverse Thanos\\'" +
                " actions and restore balance to the universe.",
        listOf(1,2,3,4),
        137,
        "@drawable/avengers_mini",
        "@drawable/avengers"
      )
    )
  }
}