package ru.android.study.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.android.study.factory.ViewModelKey
import ru.android.study.factory.ViewModelProviderFactory
import ru.android.study.ui.movies_details.view_models.MoviesDetailsViewModel
import ru.android.study.ui.movies_list.view_models.MoviesListViewModel

@Module
abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(MoviesListViewModel::class)
  abstract fun bindMoviesListViewModelViewModel(moviesListViewModel: MoviesListViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(MoviesDetailsViewModel::class)
  abstract fun bindMoviesDetailsViewModel(moviesDetailsViewModel: MoviesDetailsViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}
