package ru.android.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(),
  FragmentMoviesList.ClickListener, FragmentMoviesDetails.ClickListener {
  private val fragmentMoviesList = FragmentMoviesList()
  private val fragmentMovieDetails = FragmentMoviesDetails()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction().apply {
        add(R.id.fragments_container, fragmentMoviesList)
        commit()
      }
    }
  }

  override fun openMovieDetails() {
    supportFragmentManager.beginTransaction().apply {
      add(R.id.fragments_container, fragmentMovieDetails)
      addToBackStack(null)
      commit()
    }
  }

  override fun onBackButtonPressed() {
    val lastFragment = supportFragmentManager.fragments.last()
    supportFragmentManager.beginTransaction().apply {
      remove(lastFragment)
      commit()
    }
  }
}
