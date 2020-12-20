package ru.android.study.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import ru.android.study.R

@RunWith(MockitoJUnitRunner::class)
class LayoutCalculatorsTest {
  @Mock
  private lateinit var mockContext: Context
  @Mock
  private lateinit var mockContextResources: Resources
  @Mock
  private val displayMetrics = DisplayMetrics()
  private val density = 1f

  @Before
  fun setupMocks() {
    MockitoAnnotations.initMocks(this)
    displayMetrics.density = density
    `when`(mockContext.resources).thenReturn(mockContextResources)
    `when`(mockContextResources.getDimension(R.dimen.movie_item_width)).thenReturn(170f)
    `when`(mockContextResources.getDimension(R.dimen.movies_details_margin_horizontal)).
    thenReturn(16f)
    `when`(mockContextResources.getDimension(R.dimen.movie_details_actors_margin_horizontal)).
    thenReturn(8f)
    `when`(mockContextResources.displayMetrics).thenReturn(displayMetrics)
  }

  @Test
  fun `when small screen`() {
    displayMetrics.widthPixels = 200
    Assert.assertEquals(1, calculateMoviesListSpanCount(mockContext))
    Assert.assertEquals(36, calculateActorsHolderWidth(mockContext, 4))
  }

  @Test
  fun `when large screen`() {
    displayMetrics.widthPixels = 700
    Assert.assertEquals(4, calculateMoviesListSpanCount(mockContext))
    Assert.assertEquals(161, calculateActorsHolderWidth(mockContext, 4))
  }
}
