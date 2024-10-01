package com.example.aniwatch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val title: String,
    val genre: String,
    val releaseDate: String,
    val rating: String,
    val review: String,
    val story: String,
    val image: String,
    val imageWallpaper: String
) : Parcelable
