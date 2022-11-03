package com.example.musicplayerandroidv2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val data: ArrayList<MusicList>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        print("data")
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}