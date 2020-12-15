package ru.android.study.ui.movies_list

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.android.study.ui.movies_list.adapters.MoviesListAdapter
import ru.android.study.ui.movies_list.adapters.OnMovieClicked
import ru.android.study.R
import ru.android.study.data.MoviesService
import ru.android.study.data.model.Movie
import ru.android.study.ui.movies_details.FragmentMoviesDetails

class FragmentMoviesList : Fragment() {
  private lateinit var adapter: MoviesListAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    adapter =
      MoviesListAdapter(clickListener)
    val recycler = view.findViewById<RecyclerView>(R.id.movies_list)
    recycler.adapter = adapter
    val spanCount = calculateSpanCount()
    recycler.layoutManager = GridLayoutManager(requireContext(), spanCount)
    adapter.bindActors(MoviesService().getMovies())
  }

  private fun calculateSpanCount(): Int {
    val displayMetrics: DisplayMetrics = requireContext().resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels.div(displayMetrics.density)
    val movieItemWidth = requireContext().resources.getDimension(R.dimen.movie_item_width)
    val movieItemWidthDp = movieItemWidth.div(displayMetrics.density)
    return screenWidthDp.div(movieItemWidthDp).toInt()
  }

  private val clickListener = object :
    OnMovieClicked {
    override fun onClick(movie: Movie) {
      fragmentManager?.beginTransaction()?.
        add(R.id.fragments_container, FragmentMoviesDetails.newInstance(movie.id))?.
        addToBackStack(null)?.
        commit()
    }
  }
}
