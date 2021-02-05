package ru.android.study.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.android.study.ui.MainActivity

@Module
abstract class ActivityBuilderModule {

  @ContributesAndroidInjector(modules = [MoviesFragmentModule::class])
  abstract fun injectMainActivity(): MainActivity
}
