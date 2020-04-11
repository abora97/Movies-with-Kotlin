package com.example.movies_with_kotlin.ui.home

import PopularData
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_with_kotlin.R
import com.example.movies_with_kotlin.ui.DetailActivity
import com.example.movies_with_kotlin.util.Constants.BASE_IMAGE_URL
import com.example.movies_with_kotlin.util.Constants.MOVIE_DATA
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*


class MovieAdapter(context: Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val context: Context? = context
    private var list: List<PopularData>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.tvMovieName.text=list[position].title

        Picasso.get()
            .load(BASE_IMAGE_URL+list[position].poster_path)
            .placeholder(R.drawable.ic_popcorn)
            .error(R.drawable.ic_clapperboard)
            .into(holder.itemView.itemImage)



        holder.itemView.itemLay.setOnClickListener {

            // start detailsActivity and move data using bundle
            val intent = Intent(this.context, DetailActivity::class.java)
            val bundle = Bundle()

          //  bundle.putParcelable(MOVIE_DATA, list[position])
            bundle.putString(MOVIE_DATA,Gson().toJson(list[position]))
            intent.putExtras(bundle)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context!!.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<PopularData>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        val tvMovieName = itemView.findViewById(R.id.tvMovieName) as TextView
//        val tvMovieName: TextView = itemView.tvMovieName
//        val itemImage: ImageView =itemView.itemImage

    }

    init {
        list = ArrayList()
    }
}