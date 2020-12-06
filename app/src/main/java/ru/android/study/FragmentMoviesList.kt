package ru.android.study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.android.study.data.MoviesService
import ru.android.study.data.model.Movie

class FragmentMoviesList : Fragment() {
  private var recycler: RecyclerView? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler = view.findViewById(R.id.movies_list)
    recycler?.adapter = MoviesListAdapter(clickListener)
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
    (recycler?.adapter as? MoviesListAdapter)?.apply {
      bindActors(MoviesService().getMovies())
    }
  }

  private val clickListener = object : OnMovieClicked {
    override fun onClick(movie: Movie) {
      val movieDetailsFragment = FragmentMoviesDetails()
      val args = Bundle()
      args.putInt(FragmentMoviesDetails.MOVIE_ID, movie.id)
      movieDetailsFragment.arguments = args

      fragmentManager?.beginTransaction()?.
      add(R.id.fragments_container, movieDetailsFragment)?.
      addToBackStack(null)?.
      commit()
    }
  }
}
