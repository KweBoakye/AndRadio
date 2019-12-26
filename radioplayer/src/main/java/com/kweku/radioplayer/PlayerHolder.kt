package com.kweku.radioplayer

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class PlayerHolder(private val radioService: RadioService) {

    private lateinit var player:SimpleExoPlayer //= ExoPlayerFactory.newSimpleInstance(radioService)
    private var currentWindow: Int = 0
    private var playBackPosition: Long = 0

    fun getPlayer():SimpleExoPlayer{
        return player
    }

   fun initializePlayer(url: String){
        player = ExoPlayerFactory.newSimpleInstance(radioService)
       // playerView.player = player
        val audioUri: Uri = Uri.parse(url)
        val mediaSource = buildMediaSource(audioUri)
        with(player){
            playWhenReady = true
            seekTo(currentWindow, playBackPosition)
            prepare(mediaSource, false, false)
        }
    }

    fun preparePlayer(url: String){
        val audioUri: Uri = Uri.parse(url)
        val audioSource: MediaSource = buildMediaSource(audioUri)
        player.playWhenReady = true
        player.seekTo(0,0)
        player.prepare(audioSource,false, false)
    }

    fun playWhenReady(boolean: Boolean) {
        player.playWhenReady = boolean}

    private fun buildMediaSource(uri:Uri): MediaSource{
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(radioService,"radio-app")
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }


    fun releaseExoplayer(){
        player.release()
    }
}