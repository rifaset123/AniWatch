package com.example.aniwatch

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
//import android.graphics.Bitmap
//import android.renderscript.Allocation
//import android.renderscript.Element
//import android.renderscript.RenderScript
//import android.renderscript.ScriptIntrinsicBlur
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aniwatch.databinding.ActivityDetailPageBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlin.math.log

class DetailPage : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPageBinding

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_RELEASE_DATE = "extra_release_date"
        const val EXTRA_RATING = "extra_rating"
        const val EXTRA_GENRE = "extra_genre"
        const val EXTRA_STORY = "extra_story"
        const val EXTRA_REVIEW = "extra_review"

        const val EXTRA_ANIME = "extra_anime"
        const val EXTRA_POSITION = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)



        with(binding){
            val anime = intent.getParcelableExtra<Anime>(EXTRA_ANIME)
            anime?.let {
                animeTitle.text = it.title
                val requestOptions = RequestOptions.bitmapTransform(RoundedCornersTransformation(20, 10))
                Glide.with(this@DetailPage)
                    .load(it.image)
                    .centerCrop()
                    .apply(requestOptions)
                    .placeholder(R.drawable.placeholder_image)
                    .into(imageAnime)

                // wallpaper
                val imageWallpaperResId = resources.getIdentifier(it.imageWallpaper, "drawable", packageName)
                Glide.with(this@DetailPage)
                    .load(imageWallpaperResId)
                    .centerCrop()
                    .apply(requestOptions)
                    .into(animeWallpaper)
                animeReleaseDate.text = it.releaseDate
                animeRating.text = it.rating
                animeGenre.text = it.genre
                animeStory.text = it.story
                animeReview.text = it.review
            }

            actionShare.setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Check out this anime: ${anime?.title}\nRating: ${anime?.rating}\nReview: ${anime?.review}")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }

        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}