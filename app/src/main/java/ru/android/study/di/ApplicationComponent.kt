package ru.android.study.di

import dagger.BindsInstance
import dagger.Component
import ru.android.study.MoviesApplication
import ru.android.study.di.module.*
import javax.inject.Singleton
import dagger.android.AndroidInjectionModule

@Singleton
@Component(
  modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    RepositoriesModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    MoviesDataConverterModule::class,
    MoviesFragmentModule::class,
    WorkerModule::class
  ]
)
interface ApplicationComponent {
  fun inject(application: MoviesApplication)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: MoviesApplication): Builder
    fun appModule(module: AppModule): Builder
    fun build(): ApplicationComponent
  }
}
