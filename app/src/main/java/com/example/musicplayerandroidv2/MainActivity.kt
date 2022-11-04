package com.example.musicplayerandroidv2

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Field

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var noMusicTextView: TextView
    var songsList = ArrayList<MusicModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        noMusicTextView = findViewById(R.id.no_songs_text)

        // membaca music
        var fields: Array<Field> = R.raw::class.java.fields
        for(i in fields.indices) {
            var path = ""
            try {
                path = "android.resource://" + packageName + "/" + fields[i].getInt(fields[i])
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

            println(path)

            // membaca metadata
            var mediaPath: Uri = Uri.parse(path)
            val mediaRetriever = MediaMetadataRetriever()
            mediaRetriever.setDataSource(this, mediaPath)

            var title: String =
                mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE).toString()
            var duration: String =
                mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION).toString()

            val music = MusicModel(path, title, duration)
            songsList.add(music)
        }

        println(songsList.size)
        for (i in 0 until songsList.size) {
            println("Title: " + songsList[i].title)
            println("Duration: " + songsList[i].duration)
        }

        // test
        println("Title: " + songsList[0].title)
        if (songsList.size == 0) {
            noMusicTextView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            noMusicTextView.visibility = View.GONE
            recyclerView.adapter = MyAdapter(songsList, applicationContext)
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if(recyclerView != null) {
            recyclerView.adapter= MyAdapter(songsList, applicationContext)
        }
    }
}
