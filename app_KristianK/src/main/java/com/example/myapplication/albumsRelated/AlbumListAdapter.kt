package com.example.myapplication.albumsRelated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.user.User

class AlbumListAdapter(var albums: List<Album>, val navigate: (Int) -> Unit) :
        RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemUserNameTextView: TextView = itemView.findViewById(R.id.cardUserNameText)
        val itemUserIdTextView: TextView = itemView.findViewById(R.id.cardUserIdText)
        val itemUserEmailTextView: TextView = itemView.findViewById(R.id.email)

        init {
            itemView.setOnClickListener {
                //Refers to user object clicked and sends its index within the 'users' list.
                //User id is not related.
                navigate(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemUserNameTextView.text = albums[position].title
        holder.itemUserIdTextView.text = albums[position].id.toString()
        holder.itemUserEmailTextView.isVisible = false
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}