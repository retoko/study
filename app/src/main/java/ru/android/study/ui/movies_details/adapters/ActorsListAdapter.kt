package ru.android.study.ui.movies_details.adapters

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.android.study.R
import ru.android.study.data.model.Actor

class ActorsListAdapter : RecyclerView.Adapter<ActorViewHolder>() {

  private var actors = listOf<Actor>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
    val itemView = LayoutInflater.from(parent.context)
      .inflate(R.layout.view_holder_actor, parent, false)

    itemView.layoutParams.width = calculateActorsHolderWidth(parent.context, 4)

    return ActorViewHolder(itemView)
  }

  private fun calculateActorsHolderWidth(context: Context, actorsCount: Int): Int {
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val moviesDetailsMarginsCount = 2 // left and right
    val moviesDetailsMarginHorizontal =
      context.resources.getDimension(R.dimen.movies_details_margin_horizontal)
        .times(moviesDetailsMarginsCount)
    val actorsMarginCount = actorsCount - 1 // right margin between items = n-1
    val marginBetweenActors =
      context.resources.getDimension(R.dimen.movie_details_actors_margin_horizontal)
        .times(actorsMarginCount)
    val marginsTotal = marginBetweenActors.plus(moviesDetailsMarginHorizontal)
    return screenWidth.minus(marginsTotal).div(actorsCount).toInt()
  }

  override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
    holder.onBind(actors[position])
  }

  override fun getItemCount(): Int = actors.size

  fun bindActors(newActors: List<Actor>) {
    actors = newActors
    notifyDataSetChanged()
  }
}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  private val photo: ImageView = itemView.findViewById(R.id.photo)
  private val name: TextView = itemView.findViewById(R.id.name)

  fun onBind(actor: Actor) {
    val roundingRadius = context.resources.getDimension(R.dimen.photo_border_radius).toInt()
    val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(roundingRadius))
    Glide.with(context)
      .load(actor.picture)
      .apply(requestOptions)
      .into(photo)
    name.text = actor.name
  }

  private val RecyclerView.ViewHolder.context
    get() = this.itemView.context
}
