package ru.android.study.ui.movies_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import ru.android.study.ui.movies_details.adapters.ActorsListAdapter
import ru.android.study.R
import ru.android.study.data.model.Actor
import ru.android.study.data.model.Movie
import ru.android.study.data.network.MoviesDataSource
import ru.android.study.ui.movies_details.view_models.MoviesDetailsViewModel
import ru.android.study.ui.movies_details.view_models.MoviesDetailsViewModelFactory

class FragmentMoviesDetails : Fragment() {
  private val viewModel: MoviesDetailsViewModel by viewModels {
    MoviesDetailsViewModelFactory(MoviesDataSource())
  }
  private lateinit var adapter: ActorsListAdapter
  private lateinit var background: ImageView
  private lateinit var ageLimit: TextView
  private lateinit var genre: TextView
  private lateinit var ratingBar: SimpleRatingBar
  private lateinit var reviewsCount: TextView
  private lateinit var title: TextView
  private lateinit var storyline: TextView
  private lateinit var recycler: RecyclerView

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val backButton = view.findViewById<Button>(R.id.back_button)
    backButton.setOnClickListener {
      requireActivity().supportFragmentManager.popBackStack()
    }
    initViews(view)
    setUpAdapter()
    val movieId = arguments?.getInt(MOVIE_ID)
    viewModel.mutableMovie.observe(this.viewLifecycleOwner, this::setMovie)
    viewModel.mutableActors.observe(this.viewLifecycleOwner, this::setActors)
    viewModel.loadMovie(movieId ?: return)
  }

  private fun initViews(view: View) {
    background = view.findViewById(R.id.background)
    ageLimit = view.findViewById(R.id.age_limit)
    genre = view.findViewById(R.id.genre)
    ratingBar = view.findViewById(R.id.rating_bar)
    reviewsCount = view.findViewById(R.id.reviews_count)
    title = view.findViewById(R.id.title)
    storyline = view.findViewById(R.id.storyline_content)
    recycler = view.findViewById(R.id.actors_list)
  }

  private fun setUpAdapter() {
    adapter = ActorsListAdapter()
    recycler.adapter = adapter
  }

  private fun setActors(actors: List<Actor>) {
    adapter.bindActors(actors)
  }

  private fun setMovie(movie: Movie) {
    Glide.with(requireContext()).load(movie.backdrop).into(background)
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
