package com.kweku.core.mainactivity



import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.renderscript.RenderScript
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.kweku.core.BlurUtil
import com.kweku.core.R
import com.kweku.core.RenderScriptBlurProcessor
import com.kweku.core.databinding.ActivityMainBinding
import com.kweku.core.databinding.NowPlayingLayoutBinding
import timber.log.Timber

class MainActivityViewClass(
    val mainActivity: MainActivity, val playPauseAction: () -> Unit
) : MainActivityViewClassInterface, TransitionAdapter() {

    private val binding: ActivityMainBinding = ActivityMainBinding
        .inflate(LayoutInflater.from(mainActivity))

    private val root: View = binding.root
    private val fragmentContainerView: FragmentContainerView = binding.navHostFragment
    private val nowPlayingLayoutBinding: NowPlayingLayoutBinding = binding.nowPlayingLayout

    private val playButton: ImageButton = nowPlayingLayoutBinding.exoPlay
    private val playerBuffering: ProgressBar = nowPlayingLayoutBinding.playerBuffering
    private val stationName: TextView = nowPlayingLayoutBinding.stationName
    private val stationImage: ImageView = nowPlayingLayoutBinding.stationIcon


    val nowPlayingLayout: MotionLayout =
        nowPlayingLayoutBinding.nowPlaying

    private val renderScriptBlurProcessor: RenderScriptBlurProcessor = RenderScriptBlurProcessor(
        RenderScript.create(mainActivity))
    val blurTransition:BlurTransition = BlurTransition(mainActivity)
    private val displayMetrics: DisplayMetrics = DisplayMetrics()
    val windowManager  = mainActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)
    val screenHeight =  displayMetrics.heightPixels
    val backGroundImage: View = nowPlayingLayoutBinding.backgroundImage
    val colorAdd = Color.parseColor("#C0121D")
    val colorMultiply = Color.parseColor("#4194D7")
    val lightingColorFilterForNowPlayingLayout: LightingColorFilter = LightingColorFilter(colorMultiply,colorAdd )


   lateinit var blurBitmap: Bitmap/*.also {backGroundImage ->

        backGroundImage.translationY = (-(screenHeight/2).toFloat())
        val layoutParams: ConstraintLayout.LayoutParams = backGroundImage.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.height = fragmentContainerView.height
        layoutParams.width = fragmentContainerView.width
    }*/




    private val bottomSheetBehavior: BottomSheetBehavior<MotionLayout> = BottomSheetBehavior.from(nowPlayingLayout)


    override fun getRootView(): View = this.root

    init {

        setBottomSheetBehaviourListener()
        nowPlayingLayout.setTransitionListener(this)


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
                        blurTransition.progress = slideOffset.coerceIn(0F,1F)

                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                    }
                })
            }
        }


    override fun setStationName(stationNameString: String) {
        stationName.text = stationNameString
    }

    override fun setStationImage(drawable: Drawable) {
        stationImage.setImageDrawable(drawable)
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



    override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
        if(startId  == R.id.collapsed  ){
            blurBitmap = renderScriptBlurProcessor.blur(
                BlurUtil.getBitmapFromView( fragmentContainerView),
                15f, 3)
        }
            else if ((startId == R.id.expanded) and (bottomSheetBehavior.state == STATE_EXPANDED)){
                nowPlayingLayout.setBackgroundColor(Color.parseColor("#000000"))
            }

    }


    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
       if((currentId == R.id.expanded) and (bottomSheetBehavior.state == STATE_EXPANDED)){
           nowPlayingLayout.background = BitmapDrawable(mainActivity.resources ,blurBitmap)
           nowPlayingLayout.background.colorFilter = lightingColorFilterForNowPlayingLayout
        }

        else if (currentId == R.id.collapsed){
           nowPlayingLayout.setBackgroundColor(Color.parseColor("#000000"))
        }
    }

}