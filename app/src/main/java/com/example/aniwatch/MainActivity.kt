package com.example.aniwatch

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aniwatch.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAnime: RecyclerView
    private val list = ArrayList<Anime>()

    // intent data


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        enableEdgeToEdge()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        with(binding) {
            rvAnime = findViewById(R.id.recycle_anime)
        }

        list.addAll(getListAnime())
        showRecyclerList()
    }

    // get data from resources
    private fun getListAnime(): ArrayList<Anime> {
        val dataName = resources.getStringArray(R.array.anime_list)
        val dataImage = resources.getStringArray(R.array.anime_image)
        val dataImageWallpaper = resources.getStringArray(R.array.anime_image_wallpaper)
        val dataReleaseDate = resources.getStringArray(R.array.anime_release_date)
        val dataRating = resources.getStringArray(R.array.anime_rating)
        val dataGenre = resources.getStringArray(R.array.anime_genre)
        val dataStory = resources.getStringArray(R.array.anime_story)
        val dataReview = resources.getStringArray(R.array.anime_review)
        val listHero = ArrayList<Anime>()
        for (i in dataName.indices) {
            val hero = Anime(dataName[i],   dataGenre[i], dataReleaseDate[i],  dataRating[i],  dataStory[i], dataReview[i],  dataImage[i], dataImageWallpaper[i])
            listHero.add(hero)
        }
        return listHero
    }

    // recycleview handler
    private fun showRecyclerList() {
        rvAnime.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = AnimeAdapter(list)
        rvAnime.adapter = listAnimeAdapter

        // onClick handler
        listAnimeAdapter.setOnItemClickCallback(object : AnimeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Anime) {
                showSelectedAnime(data)
            }
        })
    }

    // onClick context
    private fun showSelectedAnime(anime: Anime) {
        val anime = Anime(anime.title, anime.genre, anime.releaseDate, anime.rating, anime.story, anime.review, anime.image, anime.imageWallpaper)

        val intentDetailPage = Intent(this, DetailPage::class.java)
        intentDetailPage.putExtra(DetailPage.EXTRA_ANIME, anime)
        startActivity(intentDetailPage)
    }

    // action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.account -> {
                val intentAccountPage = Intent(this, AccountPage::class.java)
                startActivity(intentAccountPage)
                Log.d("Intent", "Go to Account Activity Success")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

