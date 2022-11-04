package com.example.musicplayerandroidv2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import java.io.IOException
import java.util.concurrent.TimeUnit

class MusicPlayerActivity : AppCompatActivity() {

    lateinit var tvTitle: TextView
    lateinit var tvCurrentTime: TextView
    lateinit var tvTotalTime: TextView
    lateinit var seekBar: SeekBar
    lateinit var pausePlayButton: ImageView
    lateinit var nextButton: ImageView
    lateinit var prevButton: ImageView
    lateinit var musicIcon: ImageView
    lateinit var songsList: ArrayList<MusicModel>
    lateinit var currentSong: MusicModel
    val mediaPlayer = MyMediaPlayer.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        tvTitle = findViewById(R.id.song_title)
        tvCurrentTime = findViewById(R.id.current_time)
        tvTotalTime = findViewById(R.id.total_time)
        seekBar = findViewById(R.id.seek_bar)
        pausePlayButton = findViewById(R.id.pause_play)
        nextButton = findViewById(R.id.next)
        prevButton = findViewById(R.id.previous)
        musicIcon = findViewById(R.id.music_icon)

        tvTitle.isSelected = true

        songsList = intent.getSerializableExtra("LIST") as ArrayList<MusicModel>

        setResourceWithMusic()
        Thread(Runnable {
            while (true) {
                runOnUiThread {
                    if (mediaPlayer != null) {
                        seekBar.progress = mediaPlayer.currentPosition
                        tvCurrentTime.text = convertToMMSS(mediaPlayer.currentPosition.toString())

                        if (mediaPlayer.isPlaying) {
                            pausePlayButton.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
                        } else {
                            pausePlayButton.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
                        }
                    }
                }

                Thread.sleep(100)
            }
        }).start()
//        {
//            fun run() {
//                if(mediaPlayer != null) {
//                    seekBar.progress = mediaPlayer.currentPosition
//                    tvCurrentTime.text = convertToMMSS(mediaPlayer.currentPosition.toString())
//
//                    if(mediaPlayer.isPlaying) {
//                        pausePlayButton.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
//                    } else {
//                        pausePlayButton.setColorFilter(R.drawable.ic_baseline_play_circle_outline_24)
//                    }
//                }
//                Handler(Looper.getMainLooper()).postDelayed({}, 100)
//            }
//        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // here, you react to the value being set in seekBar
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })


    }

    fun setResourceWithMusic() {
        currentSong = songsList[MyMediaPlayer.currentIndex]

        tvTitle.text = currentSong.title

        tvTotalTime.text = convertToMMSS(currentSong.duration)

        pausePlayButton.setOnClickListener(View.OnClickListener { pausePlay() })
        nextButton.setOnClickListener(View.OnClickListener { playNextSong() })
        prevButton.setOnClickListener(View.OnClickListener { playPreviousSong() })

        playMusic()
    }

    fun playMusic() {
        mediaPlayer.reset()
        try {
            mediaPlayer.setDataSource(applicationContext, Uri.parse(currentSong.path))
            mediaPlayer.prepare()
            mediaPlayer.start()
            seekBar.progress = 0
            seekBar.max = mediaPlayer.duration
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun playNextSong() {
        if (MyMediaPlayer.currentIndex == songsList.size - 1) {
            return
        }

        MyMediaPlayer.currentIndex += 1
        mediaPlayer.reset()
        setResourceWithMusic()
    }

    fun playPreviousSong() {
        if (MyMediaPlayer.currentIndex == 0) {
            return
        }

        MyMediaPlayer.currentIndex -= 1
        mediaPlayer.reset()
        setResourceWithMusic()
    }

    fun pausePlay() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
    }

    companion object {
        fun convertToMMSS(duration: String): String {
            var millis = duration.toLong()
            return String.format(
                "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1)
            )
        }
    }
}


