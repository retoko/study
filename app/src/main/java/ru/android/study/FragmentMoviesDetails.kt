package ru.android.study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import ru.android.study.data.MoviesService
import ru.android.study.data.ActorsService
import ru.android.study.data.model.Movie

class FragmentMoviesDetails : Fragment() {
  private var adapter: ActorsListAdapter = ActorsListAdapter()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val backButton = view.findViewById<Button>(R.id.back_button)
    backButton.setOnClickListener {
      fragmentManager?.popBackStack()
    }
    val recycler = view.findViewById<RecyclerView>(R.id.actors_list)
    recycler.adapter = adapter
    val movieId = arguments?.getInt(MOVIE_ID)
    val movie = MoviesService().getMovie(movieId ?: return)
    adapter.bindActors(ActorsService().getActorsForMovie(movieId))
    setMovieData(view, movie ?: return)
  }

  private fun setMovieData(view: View, movie: Movie) {
    val background: ImageView = view.findViewById(R.id.background)
    val ageLimit: TextView = view.findViewById(R.id.age_limit)
    val genre: TextView = view.findViewById(R.id.genre)
    val ratingBar: SimpleRatingBar = view.findViewById(R.id.rating_bar)
    val reviewsCount: TextView = view.findViewById(R.id.reviews_count)
    val title: TextView = view.findViewById(R.id.title)
    val storyline: TextView = view.findViewById(R.id.storyline_content)

    background.setImageResource(movie.background)
    ageLimit.text = getString(R.string.age_limit, movie.ageLimit)
    genre.text = movie.genre
    ratingBar.rating = movie.rating
    reviewsCount.text = getString(R.string.reviews_count, movie.reviewsCount)
    title.text = movie.name
    storyline.text = movie.storyline
  }

  companion object {
    private const val MOVIE_ID = "movie_id"

    fun newInstance(movieId: Int): FragmentMoviesDetails {
      val movieDetailsFragment = FragmentMoviesDetails()
      val bundle = Bundle()
      bundle.putInt(MOVIE_ID, movieId)
      movieDetailsFragment.arguments = bundle
      return movieDetailsFragment
    }
  }
}
