package com.kweku.core.mainactivity



import android.content.Context
import android.os.Bundle
import android.renderscript.RenderScript
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
import androidx.fragment.app.FragmentContainerView
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.kweku.andradio.domain.models.PlayableStation
import com.kweku.andradio.domain.models.Station
import com.kweku.core.R
import com.kweku.core.RenderScriptBlurProcessor
import com.kweku.core.databinding.ActivityMainBinding
import com.kweku.core.databinding.NowPlayingLayoutBinding
import timber.log.Timber

class MainActivityViewClass(
    val mainActivity: MainActivity, val playPauseAction: () -> Unit
) : MainActivityViewClassInterface {

    private val binding: ActivityMainBinding = ActivityMainBinding
        .inflate(LayoutInflater.from(mainActivity))
    private val root: View = binding.root
    private val fragmentContainerView: FragmentContainerView = binding.navHostFragment
    val nowPlayingLayoutBinding: NowPlayingLayoutBinding = binding.nowPlayingLayout
    val playButton: ImageButton = nowPlayingLayoutBinding.exoPlay
    val playerBuffering: ProgressBar = nowPlayingLayoutBinding.playerBuffering
    val stationName: TextView = nowPlayingLayoutBinding.stationName
    val stationImage: ImageView = nowPlayingLayoutBinding.stationIcon
    val renderScriptBlurProcessor: RenderScriptBlurProcessor = RenderScriptBlurProcessor(
        RenderScript.create(mainActivity))
    val nowPlayingLayout: MotionLayout =
        nowPlayingLayoutBinding.nowPlaying/*.also { nowPlayingLayout ->
            nowPlayingLayout.setTransitionListener(this)
        }*/


    val bottomSheetBehavior: BottomSheetBehavior<MotionLayout> = BottomSheetBehavior.from(nowPlayingLayout)


    override fun getRootView(): View = this.root

    init {

        setBottomSheetBehaviourListener()
    }

     override fun setPlayButtonOnClickListener() {
        playButton.setOnClickListener {
            playPauseAction.invoke()
            Timber.i("clicked")
        }

    }

    private fun setBottomSheetBehaviourListener() {
        with(bottomSheetBehavior) {


                addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        /*use coerceIn as we are only interested in values between 0 and 1 which is
                        represent collapse and expanded states*/
                        nowPlayingLayout.progress =  slideOffset.coerceIn(0F, 1F)
                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {

                    }

                })

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

   /* override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

    }

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

    }

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

    }

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

    }*/


}