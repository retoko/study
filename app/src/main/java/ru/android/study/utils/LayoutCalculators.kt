package ru.android.study.utils

import android.content.Context
import android.util.DisplayMetrics
import ru.android.study.R

internal fun calculateMoviesListSpanCount(context: Context): Int {
  val displayMetrics: DisplayMetrics = context.resources.displayMetrics
  val screenWidthDp = displayMetrics.widthPixels.div(displayMetrics.density)
  val movieItemWidth = context.resources.getDimension(R.dimen.movie_item_width)
  val movieItemWidthDp = movieItemWidth.div(displayMetrics.density)
  return screenWidthDp.div(movieItemWidthDp).toInt()
}

internal fun calculateActorsHolderWidth(context: Context, actorsCount: Int): Int {
  val displayMetrics: DisplayMetrics = context.resources.displayMetrics
  val screenWidth = displayMetrics.widthPixels
  val moviesDetailsMarginsCount = 2 // left and right
  val moviesDetailsMarginHorizontal =
    context.resources.getDimension(R.dimen.movies_details_margin_horizontal).
      times(moviesDetailsMarginsCount)
  val actorsMarginCount = actorsCount - 1 // right margin between items = n-1
  val marginBetweenActors =
    context.resources.getDimension(R.dimen.movie_details_actors_margin_horizontal).
      times(actorsMarginCount)
  val marginsTotal = marginBetweenActors.plus(moviesDetailsMarginHorizontal)
  return screenWidth.minus(marginsTotal).div(actorsCount).toInt()
}
