package ru.android.study.ui.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.android.study.ui.movies_list.adapters.MoviesListAdapter
import ru.android.study.ui.movies_list.adapters.OnMovieClicked
import ru.android.study.R
import ru.android.study.data.model.Movie
import ru.android.study.ui.movies_details.FragmentMoviesDetails
import ru.android.study.ui.movies_list.view_models.MoviesListViewModel
import ru.android.study.utils.calculateMoviesListSpanCount

class FragmentMoviesList : Fragment() {
  private lateinit var adapter: MoviesListAdapter
  private lateinit var recycler: RecyclerView
  private val viewModel = MoviesListViewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews(view)
    setUpAdapter()
    viewModel.mutableMoviesList.observe(this.viewLifecycleOwner, this::setMovies)
    viewModel.loadMovies(requireContext())
  }

  private fun initViews(view: View) {
    recycler = view.findViewById(R.id.movies_list)
  }

  private fun setUpAdapter() {
    adapter = MoviesListAdapter(clickListener)
    recycler.adapter = adapter
    val spanCount = calculateMoviesListSpanCount(requireContext())
    recycler.layoutManager = GridLayoutManager(requireContext(), spanCount)
  }

  private fun setMovies(movies: List<Movie>) {
    adapter.bindMovies(movies)
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
