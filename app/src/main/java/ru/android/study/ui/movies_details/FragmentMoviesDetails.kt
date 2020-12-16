package ru.android.study.ui.movies_details

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
import com.bumptech.glide.Glide
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import kotlinx.coroutines.*
import ru.android.study.ui.movies_details.adapters.ActorsListAdapter
import ru.android.study.R
import ru.android.study.data.MoviesService

class FragmentMoviesDetails : Fragment() {
  private lateinit var adapter: ActorsListAdapter
  private val coroutineScope = CoroutineScope(Dispatchers.Main)
  private val moviesService = MoviesService()

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
    adapter = ActorsListAdapter()
    val recycler = view.findViewById<RecyclerView>(R.id.actors_list)
    recycler.adapter = adapter
    val movieId = arguments?.getInt(MOVIE_ID)
    coroutineScope.launch {
      setMovieData(requireContext(), view, adapter, movieId)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    coroutineScope.cancel()
  }

  private suspend fun setMovieData(
    context: Context, view: View, adapter: ActorsListAdapter, movieId: Int?
  ) {
    movieId ?: return
    val movie = moviesService.getMovie(context, movieId) ?: return
    adapter.bindActors(movie.actors)

    val background: ImageView = view.findViewById(R.id.background)
    val ageLimit: TextView = view.findViewById(R.id.age_limit)
    val genre: TextView = view.findViewById(R.id.genre)
    val ratingBar: SimpleRatingBar = view.findViewById(R.id.rating_bar)
    val reviewsCount: TextView = view.findViewById(R.id.reviews_count)
    val title: TextView = view.findViewById(R.id.title)
    val storyline: TextView = view.findViewById(R.id.storyline_content)

    Glide.with(requireContext())
      .load(movie.backdrop)
      .into(background)

    ageLimit.text = getString(R.string.age_limit, movie.minimumAge)
    genre.text = movie.genres.joinToString(separator = ", ", transform = { it.name })
    ratingBar.rating = movie.ratings.div(2)
    reviewsCount.text = getString(R.string.reviews_count, movie.numberOfRatings)
    title.text = movie.title
    storyline.text = movie.overview
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
