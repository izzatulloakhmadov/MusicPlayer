package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var runnable: Runnable
    private var handler = Handler()


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.uzmir)

        binding.seekbar.progress = 0

        binding.seekbar.max = mediaPlayer.duration



        binding.playBtn.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                binding.playBtn.setImageResource(R.drawable.ic_baseline_pause_24)

            } else {
                mediaPlayer.pause()
                binding.playBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
        }

        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    mediaPlayer.seekTo(p1)

                }

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        runnable = Runnable {
            binding.seekbar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 100)

        mediaPlayer.setOnCompletionListener {
            binding.playBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            binding.seekbar.progress=0




        }

    }
}