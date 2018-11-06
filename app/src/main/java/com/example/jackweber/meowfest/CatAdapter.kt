package com.example.jackweber.meowfest

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class CatAdapter(private val cats: List<RetroCat>, val context: Context): RecyclerView.Adapter<CatAdapter.CatViewHolder>() {


    class CatViewHolder(var mView: ImageView): RecyclerView.ViewHolder(mView)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val imageView = layoutInflater.inflate(R.layout.image_row, p0, false) as ImageView
        return CatViewHolder(imageView)
    }

    override fun onBindViewHolder(p0: CatViewHolder, p1: Int) {
        val picassoBuilder = Picasso.Builder(context)
        picassoBuilder.downloader(OkHttp3Downloader(context))
        picassoBuilder.build()
                .load(cats[p1].url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(p0.mView)
    }

    override fun getItemCount(): Int {
        return cats.size
    }
}