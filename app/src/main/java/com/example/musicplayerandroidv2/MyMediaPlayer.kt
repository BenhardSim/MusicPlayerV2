package com.example.musicplayerandroidv2

import android.media.MediaPlayer

class MyMediaPlayer{
    companion object {
        var instance: MediaPlayer? = null

        @JvmName("getInstance1")
        fun getInstance(): MediaPlayer {
            if(instance == null){
                instance = MediaPlayer()
            }
            return instance as MediaPlayer
        }

        var currentIndex: Int = -1
    }

}