package com.example.movies_with_kotlin.ui.home

import PopularResponse
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_with_kotlin.R
import com.example.movies_with_kotlin.api.APIInterface
import com.example.movies_with_kotlin.api.ApiClient
import com.example.movies_with_kotlin.util.Constants.API_KEY_VALUE
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private var movieLayoutManager: RecyclerView.LayoutManager? = null
    private var movieAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        title = "Home"

        getDataFromApi()

    }

    private fun getDataFromApi() {

        val apiService: APIInterface = ApiClient.getClient()!!.create(APIInterface::class.java)


        val call: Call<PopularResponse> = apiService.getPopular(API_KEY_VALUE, "en", 1)


        call.enqueue(object : Callback<PopularResponse> {
            override fun onResponse(
                call: Call<PopularResponse>,
                response: Response<PopularResponse>
            ) {


                initRecycle()

//                movieAdapter?.setList(response.body()?.results)
                movieAdapter?.setList(response.body()?.results!!)
                response.body()?.results?.let { movieAdapter?.setList(it) }



            }

            override fun onFailure(
                call: Call<PopularResponse>,
                t: Throwable
            ) {
                Log.e("out", t.toString())
//                progressNews.setVisibility(View.GONE)
            }
        })
    }

    private fun initRecycle() {
        movieAdapter = MovieAdapter()
        rec.layoutManager = getLayoutManager()
        rec.adapter = movieAdapter
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager? {
        if (movieLayoutManager == null) {
            movieLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
        return movieLayoutManager
    }
}