package com.example.myplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var playerViewModel: PlayerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerViewModel=ViewModelProvider(this).get(PlayerViewModel::class.java).apply {
            progressBarVisibility.observe(this@MainActivity, Observer {
                progressBar.visibility=it
            })
            //resize
        }
        lifecycle.addObserver(playerViewModel.mediaplayer)
        surfaceView.holder.addCallback(object :SurfaceHolder.Callback{
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
                playerViewModel.mediaplayer.setDisplay(holder)
                playerViewModel.mediaplayer.setScreenOnWhilePlaying(true)
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {

            }

            override fun surfaceCreated(holder: SurfaceHolder?) {

            }
        })

    }
}