package ru.android.study.ui.movies_list

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkManager
import ru.android.study.ui.movies_list.adapters.MoviesListAdapter
import ru.android.study.ui.movies_list.adapters.OnMovieClicked
import ru.android.study.R
import ru.android.study.data.MoviesDataConverter
import ru.android.study.data.db.MoviesDataBase
import ru.android.study.data.model.Movie
import ru.android.study.data.network.retrofit.MoviesApiClient
import ru.android.study.data.network.retrofit.MoviesApiService
import ru.android.study.data.repositories.ActorsRepository
import ru.android.study.data.repositories.MoviesRepository
import ru.android.study.jobs.MoviesWorkRepository
import ru.android.study.ui.movies_details.FragmentMoviesDetails
import ru.android.study.ui.movies_list.view_models.MoviesListViewModel
import ru.android.study.ui.movies_list.view_models.MoviesListViewModelFactory

class FragmentMoviesList : Fragment() {
  private lateinit var adapter: MoviesListAdapter
  private lateinit var recycler: RecyclerView
  private lateinit var database: MoviesDataBase
  private lateinit var moviesApiClint: MoviesApiService
  private lateinit var moviesDataConverter: MoviesDataConverter
  private lateinit var moviesRepository: MoviesRepository
  private lateinit var actorsRepository: ActorsRepository
  private lateinit var viewModelFactory: MoviesListViewModelFactory
  private lateinit var viewModel: MoviesListViewModel
  private lateinit var moviesWorkRepository: MoviesWorkRepository

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    database = MoviesDataBase.create(requireContext())
    moviesApiClint = MoviesApiClient.moviesApiClient
    moviesDataConverter = MoviesDataConverter()
    actorsRepository = ActorsRepository(moviesApiClint, database.actorsDao)
    moviesRepository = MoviesRepository(
      moviesApiClint,
      moviesDataConverter,
      database.moviesDao
    )
    viewModelFactory = MoviesListViewModelFactory(moviesRepository)
    viewModel = ViewModelProvider(this, viewModelFactory)
      .get(MoviesListViewModel::class.java)
    moviesWorkRepository = MoviesWorkRepository()

    initBackgroundTask()
    initViews(view)
    setUpAdapter()
    viewModel.mutableMoviesList.observe(this.viewLifecycleOwner, this::setMovies)
    viewModel.loadMovies()
  }

  private fun initBackgroundTask() {
    WorkManager.getInstance(requireContext())
      .enqueue(moviesWorkRepository.constrainedPeriodicRequest)
  }

  private fun initViews(view: View) {
    recycler = view.findViewById(R.id.movies_list)
  }

  private fun setUpAdapter() {
    adapter = MoviesListAdapter(clickListener)
    recycler.adapter = adapter
    val spanCount = calculateSpanCount()
    recycler.layoutManager = GridLayoutManager(requireContext(), spanCount)
  }

  private fun setMovies(movies: List<Movie>) {
    adapter.bindMovies(movies)
  }

  private fun calculateSpanCount(): Int {
    val displayMetrics: DisplayMetrics = requireContext().resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels.div(displayMetrics.density)
    val movieItemWidth = requireContext().resources.getDimension(R.dimen.movie_item_width)
    val movieItemWidthDp = movieItemWidth.div(displayMetrics.density)
    return screenWidthDp.div(movieItemWidthDp).toInt()
  }

  private val clickListener = object : OnMovieClicked {
    override fun onClick(movie: Movie) {
      requireActivity().supportFragmentManager.beginTransaction().
        add(R.id.fragments_container, FragmentMoviesDetails.newInstance(movie.id)).
        addToBackStack(null).
        commit()
    }
  }
}
