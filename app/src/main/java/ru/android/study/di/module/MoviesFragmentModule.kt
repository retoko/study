package ru.android.study.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.android.study.ui.movies_details.FragmentMoviesDetails
import ru.android.study.ui.movies_list.FragmentMoviesList

@Module
abstract class MoviesFragmentModule {

  @ContributesAndroidInjector
  abstract fun contributeMoviesDetailsFragment(): FragmentMoviesDetails

  @ContributesAndroidInjector
  abstract fun contributeMoviesListFragment(): FragmentMoviesList
}
