package com.example.myapplication.photosListRelated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.myapplication.R
import com.example.myapplication.Settings

class PhotosListAdapter(val photos: List<Photo>, val navigate: (Int) -> Unit):
    RecyclerView.Adapter<PhotosListAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardTitle: TextView = itemView.findViewById(R.id.cardTitle)
        val imgCardView: ImageView = itemView.findViewById(R.id.cardImg)

        init {
            itemView.setOnClickListener {
                navigate(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.photo_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardTitle.text = photos[position].title
        val showThumbnail = Settings(holder.itemView.context).thumbnail
        holder.imgCardView.isVisible = showThumbnail
        if (!showThumbnail){
            val url = GlideUrl(photos[position].thumbnailUrl, LazyHeaders.Builder()
                .addHeader("User-Agent", "android")
                .build())
            Glide.with(holder.itemView.context)
                .load(url)
                .into(holder.imgCardView)
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}
