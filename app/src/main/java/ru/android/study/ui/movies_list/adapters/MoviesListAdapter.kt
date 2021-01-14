package ru.android.study.ui.movies_list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.android.study.data.model.Movie
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import ru.android.study.R

class MoviesListAdapter(
  private val clickListener: OnMovieClicked
) : RecyclerView.Adapter<MovieViewHolder>() {

  private var movies = listOf<Movie>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    return MovieViewHolder(
      LayoutInflater.from(parent.context)
        .inflate(R.layout.view_holder_movie, parent, false)
    )
  }

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    holder.onBind(movies[position])
    holder.itemView.setOnClickListener {
      clickListener.onClick(movies[position])
    }
  }

  override fun getItemCount(): Int = movies.size

  fun bindMovies(newMovies: List<Movie>) {
    movies = newMovies
    notifyDataSetChanged()
  }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  private val photo: ImageView = itemView.findViewById(R.id.photo)
  private val like: ImageView = itemView.findViewById(R.id.like)
  private val ageLimit: TextView = itemView.findViewById(R.id.age_limit)
  private val genre: TextView = itemView.findViewById(R.id.genre)
  private val ratingBar: SimpleRatingBar = itemView.findViewById(R.id.rating_bar)
  private val reviewsCount: TextView = itemView.findViewById(R.id.reviews_count)
  private val title: TextView = itemView.findViewById(R.id.title)

  fun onBind(movie: Movie) {
    val roundingRadius = context.resources.getDimension(R.dimen.photo_border_radius).toInt()
    val requestOptions = RequestOptions().transform(RoundedCorners(roundingRadius))
    Glide.with(context)
      .load(movie.poster)
      .apply(requestOptions)
      .into(photo)

    like.setImageResource(
      when (false) { // temporary set always false
        true -> R.drawable.ic_like
        false -> R.drawable.ic_no_like
      }
    )
    ageLimit.text = context.getString(R.string.age_limit, movie.minimumAge)
    genre.text = movie.genres.joinToString(separator = ", ", transform = { it.name })
    ratingBar.rating = movie.ratings.div(2)
    reviewsCount.text = context.getString(R.string.reviews_count, movie.numberOfRatings)
    title.text = movie.title
  }
}

private val RecyclerView.ViewHolder.context
  get() = this.itemView.context

interface OnMovieClicked {
  fun onClick(movie: Movie)
}
