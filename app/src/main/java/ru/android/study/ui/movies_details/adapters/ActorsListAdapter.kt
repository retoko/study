package ru.android.study.ui.movies_details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.android.study.R
import ru.android.study.data.model.Actor

class ActorsListAdapter() : RecyclerView.Adapter<ActorViewHolder>() {

  private var actors = listOf<Actor>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
    val itemView = LayoutInflater.from(parent.context)
      .inflate(R.layout.view_holder_actor, parent, false)

    return ActorViewHolder(itemView)
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
    photo.setImageResource(actor.avatar)
    name.text = actor.name
  }
}
