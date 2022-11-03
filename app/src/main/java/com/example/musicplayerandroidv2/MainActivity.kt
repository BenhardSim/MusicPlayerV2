package com.example.musicplayerandroidv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerview : RecyclerView
    lateinit var adapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }


    private fun init(){
        recyclerview = findViewById(R.id.recycle_view)

        var data = ArrayList<MusicList>()
        data.add(MusicList("Indonesia Raya", "W.R Soepratman"))
        data.add(MusicList("Perfect", "Ed Shereen"))
        data.add(MusicList("Merah Putih", "W.R Soepratman"))

        adapter = MyAdapter(data)

    }
}