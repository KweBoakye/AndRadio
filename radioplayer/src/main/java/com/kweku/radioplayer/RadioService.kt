package com.kweku.radioplayer

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory


class RadioService(): Service() {

    companion object{
        const val PLAYABLE_URL = "PlayableUrl"
        const val PLAY_PAUSE_ACTION = "playPauseAction"
    }

   // private val playerHolder:PlayerHolder = PlayerHolder(this)
    private lateinit var player: SimpleExoPlayer //= ExoPlayerFactory.newSimpleInstance(radioService)
    private var currentWindow: Int = 0
    private var playBackPosition: Long = 0
    private var currentlyPlayingUrl: String = ""


    override fun onCreate() {
        super.onCreate()
        player =  ExoPlayerFactory.newSimpleInstance(this)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let {
            when (it.getIntExtra(PLAY_PAUSE_ACTION, -1)) {
                0 -> player.playWhenReady = !player.playWhenReady
            }
        }
            return super.onStartCommand(intent, flags, startId)
        }


    override fun onBind(intent: Intent?): IBinder? {
        intent?.let { player.playWhenReady = true
            val url:String? = intent.getStringExtra(PLAYABLE_URL)

            if (url != null && url!= currentlyPlayingUrl){
                currentlyPlayingUrl = url
                startPlayback(url)}
            }
                return RadioServiceBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }




    inner class RadioServiceBinder: Binder(){
        fun getPlayerInstance() = player
        fun getService(): RadioService = this@RadioService
        fun startPlayback(url: String) = this@RadioService.startPlayback(url)
    }

    fun initializePlayer(url: String){
        player = ExoPlayerFactory.newSimpleInstance(this)
        // playerView.player = player
       startPlayback(url)
    }

    fun startPlayback(url:String){
        val audioUri: Uri = Uri.parse(url)
        val mediaSource = buildMediaSource(audioUri)
        with(player){
            playWhenReady = true
            seekTo(currentWindow, playBackPosition)
            prepare(mediaSource, false, false)
        }
    }

    private fun buildMediaSource(uri:Uri): MediaSource {
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(this,"radio-app")
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }


}