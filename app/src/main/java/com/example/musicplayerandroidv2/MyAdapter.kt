package com.example.musicplayerandroidv2

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val songsList: ArrayList<MusicModel>, val context: Context): RecyclerView.Adapter<ViewHolder>() {
    val mainActivity = MainActivity()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.listlagu_template, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val songData = songsList[position]
        holder.tvTitle.text = songData.title

        if(MyMediaPlayer.currentIndex == position) {
            holder.tvTitle.setTextColor(Color.parseColor("#14C38E"))
        } else {
            holder.tvTitle.setTextColor(Color.parseColor("#FFFFFF"))
        }

        holder.itemView.setOnClickListener(View.OnClickListener { view ->
            MyMediaPlayer.getInstance().reset()
            MyMediaPlayer.currentIndex = position
            val intent = Intent(context, MusicPlayerActivity::class.java)
            intent.putExtra("LIST", songsList)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
        )
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

}