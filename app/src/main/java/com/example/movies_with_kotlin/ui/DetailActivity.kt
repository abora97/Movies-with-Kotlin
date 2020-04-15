package com.example.movies_with_kotlin.ui

import PopularData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.movies_with_kotlin.R
import com.example.movies_with_kotlin.util.Constants
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_movie.view.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
    }

    private fun init() {
        val movieData= Gson().fromJson(intent.getStringExtra(Constants.MOVIE_DATA),PopularData::class.java)
        Picasso.get()
            .load(Constants.BASE_IMAGE_URL +movieData.poster_path)
            .placeholder(R.drawable.ic_popcorn)
            .error(R.drawable.ic_clapperboard)
            .into(ivDetails)
        tvDetailsTitle.text = movieData.title
        tvDetailsDate.text=movieData.release_date

    }
}
