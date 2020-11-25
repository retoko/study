package ru.android.study

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {
  private var textOpenDetails: TextView? = null
  private var listener: ClickListener? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    textOpenDetails = view.findViewById<TextView>(R.id.movie).apply {
      setOnClickListener { listener?.openMovieDetails() }
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is ClickListener) {
      listener = context
    }
  }

  override fun onDetach() {
    super.onDetach()
    listener = null
  }

  interface ClickListener {
    fun openMovieDetails()
  }
}
