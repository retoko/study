package ru.android.study

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.android.study.di.ApplicationComponent
import ru.android.study.di.DaggerApplicationComponent
import ru.android.study.di.module.AppModule
import ru.android.study.jobs.MoviesWorkScheduler
import javax.inject.Inject

class MoviesApplication: Application(), HasAndroidInjector {

  @Inject
  lateinit var injector: DispatchingAndroidInjector<Any>

  private lateinit var component: ApplicationComponent
  private lateinit var workManager: WorkManager

  val appComponent: ApplicationComponent
    get() = component

  override fun androidInjector(): AndroidInjector<Any> = injector

  override fun onCreate() {
    super.onCreate()
    component = DaggerApplicationComponent.builder()
      .application(this)
      .appModule(AppModule(this))
      .build()

    component.inject(this)

    workManager = WorkManager.getInstance(applicationContext)
    MoviesWorkScheduler(workManager).startBackgroundMoviesPreload()
  }
}
