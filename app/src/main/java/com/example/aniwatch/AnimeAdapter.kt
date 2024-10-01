package com.example.aniwatch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class AnimeAdapter(private val listAnime: ArrayList<Anime>)  : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val imgAnime: ImageView = itemView.findViewById(R.id.imageAnime)
        val animeName: TextView = itemView.findViewById(R.id.animeTitle)
        val animeRelease: TextView = itemView.findViewById(R.id.animeReleaseDate)
        val animeGenre: TextView = itemView.findViewById(R.id.animeGenre)
        val animeRating: TextView = itemView.findViewById(R.id.animeRating)
        val animeOverview: TextView = itemView.findViewById(R.id.animeOverview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_container, parent, false)
        return AnimeViewHolder(view)
    }

    override fun getItemCount(): Int = listAnime.size


    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val (name, genre, release, rating, story, review, image, imageWallpaper) = listAnime[position]
        val requestOptions = RequestOptions.bitmapTransform(RoundedCornersTransformation(30, 10))
        Glide.with(holder.itemView.context)
            .load(image) // URL Gambar
            .centerCrop()
            .placeholder(R.drawable.placeholder_image)
            .apply(requestOptions)
            .into(holder.imgAnime) // imageView mana yang akan diterapkan
        holder.animeName.text = name
        holder.animeGenre.text = genre
        holder.animeRating.text = rating
        holder.animeRelease.text = release
        holder.animeOverview.text = story
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listAnime[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Anime)
    }

}