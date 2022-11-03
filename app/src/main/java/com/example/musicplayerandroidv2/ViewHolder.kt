package com.example.musicplayerandroidv2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerandroidv2.R

class ViewHolder(inflater : LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.listlagu_template, parent, false)) {

    private var txtJudul : TextView? = null
    private var txtPenulis : TextView? = null

    init {
        txtJudul = itemView.findViewById(R.id.JudulLagu)
        txtPenulis = itemView.findViewById(R.id.pengarang)
    }

    fun bind(data : MusicList){
        txtJudul?.text = data.txtJudul
        txtPenulis?.text = data.txtPenulis
    }

}