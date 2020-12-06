package ru.android.study

import android.content.Context
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

class FragmentMoviesDetails : Fragment() {
  private var movieId: Int? = null;
  private var recycler: RecyclerView? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    movieId = arguments?.getInt(MOVIE_ID);
    return inflater.inflate(R.layout.fragment_movies_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler = view.findViewById(R.id.actors_list)
    recycler?.adapter = ActorsListAdapter()
    val backButton = view.findViewById<Button>(R.id.back_button)
    backButton.setOnClickListener {
      fragmentManager?.popBackStack()
    }
    setMovieData(view)
  }

  override fun onStart() {
    super.onStart()
    updateData()
  }

  override fun onDetach() {
    recycler = null
    super.onDetach()
  }

  private fun updateData() {
    (recycler?.adapter as? ActorsListAdapter)?.apply {
      bindActors(ActorsService().getActorsForMovie(movieId))
    }
  }

  private fun setMovieData(view: View) {
    val movie = MoviesService().getMovie(movieId)
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
    const val MOVIE_ID = "movie_id"
  }
}
