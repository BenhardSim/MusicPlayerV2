package com.example.musicplayerandroidv2

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class ServiceLagu : Service() {

    private lateinit var media: MediaPlayer
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val type = intent.getStringExtra("type")
        if (type == "Lagu1") {
            media = MediaPlayer.create(this, R.raw.pianopaduansuarastanzasatu)
        } else if (type == "Lagu2") {
            media = MediaPlayer.create(this, R.raw.unisonostanzastanza)
        } else if (type == "Lagu3") {
            media = MediaPlayer.create(this, R.raw.unisonostanzatiga)
        }
        media.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        media.stop()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}