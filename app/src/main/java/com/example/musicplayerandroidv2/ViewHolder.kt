package com.example.musicplayerandroidv2

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent
import java.nio.file.Files.find

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvTitle: TextView = itemView.findViewById(R.id.music_title_text)
    val tvIconImage: ImageView = itemView.findViewById(R.id.icon_view)
}