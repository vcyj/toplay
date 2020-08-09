package com.example.myplayer

import android.graphics.Point
import android.media.MediaPlayer
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayerViewModel:ViewModel() {
    val mediaplayer=MyMediaPlayer()
    private val _progreassBarVisibility= MutableLiveData(View.VISIBLE)
    val progressBarVisibility:LiveData<Int> get()  = _progreassBarVisibility
    private val _videoResolution= MutableLiveData(Pair(0,0))
    val videoResolution:LiveData<Pair<Int,Int>> get()=_videoResolution
    init {
        loadVideo()
    }
    private fun loadVideo(){
        mediaplayer.apply {
            _progreassBarVisibility
            setDataSource("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218093206z8V1JuPlpe.mp4")
            setOnPreparedListener{
                _progreassBarVisibility.value=View.INVISIBLE
                isLooping=true
                it.start()
            }
            setOnVideoSizeChangedListener{ mp, width, height ->
                _videoResolution.value= Pair(width,height)
            }
            prepareAsync()
        }

    }

    override fun onCleared() {
        super.onCleared()
        mediaplayer.release()
    }
}