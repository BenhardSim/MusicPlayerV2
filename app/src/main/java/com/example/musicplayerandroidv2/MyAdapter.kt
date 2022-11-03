package com.example.musicplayerandroidv2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val songsList: ArrayList<MusicModel>, context: Context): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.listlagu_template, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val songData = songsList[position]
        holder.tvTitle.text = songData.title
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

}