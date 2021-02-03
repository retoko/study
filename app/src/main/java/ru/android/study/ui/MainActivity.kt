package ru.android.study.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.android.study.R
import ru.android.study.ui.movies_list.FragmentMoviesList

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction().
        add(
          R.id.fragments_container,
          FragmentMoviesList()
        ).
        commit()
    }
  }
}
