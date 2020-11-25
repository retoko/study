package ru.android.study

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentMoviesDetails : Fragment() {
  private var backButton: Button? = null
  private var listener: ClickListener? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    backButton = view.findViewById<Button>(R.id.back_button).apply {
      setOnClickListener { listener?.onBackButtonPressed() }
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
    fun onBackButtonPressed()
  }
}