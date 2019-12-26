package com.kweku.core.mainactivity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.kweku.andradio.domain.models.PlayableStation
import com.kweku.andradio.domain.models.Station
import com.kweku.core.R
import com.kweku.core.databinding.ActivityMainBinding
import com.kweku.core.databinding.NowPlayingLayoutBinding

class MainActivityViewClass(
    val mainActivity: MainActivity, val playPauseAction: Unit
) : MainActivityViewClassInterface, TransitionListener {

    private val binding: ActivityMainBinding = ActivityMainBinding
        .inflate(LayoutInflater.from(mainActivity))
    private val root: View = binding.root
    val nowPlayingLayoutBinding: NowPlayingLayoutBinding = binding.nowPlayingLayout
    val playButton: ImageButton = nowPlayingLayoutBinding.exoPlay
    val playerBuffering: ProgressBar = nowPlayingLayoutBinding.playerBuffering
    val stationName: TextView = nowPlayingLayoutBinding.stationName
    val stationImage: ImageView = nowPlayingLayoutBinding.stationIcon
    val nowPlayingLayout: MotionLayout =
        nowPlayingLayoutBinding.nowPlaying.also { nowPlayingLayout ->
            nowPlayingLayout.setTransitionListener(this)
        }
    val bottomSheetBehavior: BottomSheetBehavior<MotionLayout> =
        BottomSheetBehavior.from(nowPlayingLayout)


    override fun getRootView(): View = this.root

    private fun setPlayButtonOnClickListener() {
        playButton.setOnClickListener {
            playPauseAction
        }
    }

    private fun setBottomSheetBehaviourListener(savedInstanceState: Bundle?) {
        with(bottomSheetBehavior) {
            if (savedInstanceState == null) {
                state = STATE_HIDDEN
                addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        /*use coerceIn as we are only interested in values between 0 and 1 which is
                        represent collapse and expanded states*/
                        nowPlayingLayout.progress = 1 - slideOffset.coerceIn(0F, 1F)
                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {

                    }

                })

            }
        }
    }

    override fun setStationName(playableStation: PlayableStation) {
        stationName.text = playableStation.stationName
    }

    override fun setStationImage(station: Station) {
        stationImage.load(station.favicon)
    }

    override fun setPlaybackControlsToPlaying() {
        playButton.setImageResource(R.drawable.exo_controls_pause)
        playButton.visibility = View.VISIBLE
        playerBuffering.visibility = View.GONE
    }

    override fun setPlaybackControlsToBuffering() {
        playButton.visibility = View.GONE
        playerBuffering.visibility = View.VISIBLE
    }

    override fun setPlaybackControlsToStopped() {
        playButton.setImageResource(R.drawable.exo_controls_play)
        playButton.visibility = View.VISIBLE
        playerBuffering.visibility = View.GONE
    }

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}