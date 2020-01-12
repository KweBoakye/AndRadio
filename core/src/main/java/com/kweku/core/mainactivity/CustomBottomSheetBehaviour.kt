package com.kweku.core.mainactivity

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kweku.core.R
import com.kweku.core.databinding.NowPlayingLayoutBinding
import kotlinx.android.synthetic.main.now_playing_layout.view.*

class CustomBottomSheetBehaviour<V: View>: BottomSheetBehavior<V> {

    constructor(): super()
    constructor(
        context: Context,
        attrs: AttributeSet
    ): super(context, attrs)

    private lateinit var playButton: ImageButton

    fun setPlayButton(playButton: ImageButton){
        this.playButton = playButton
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: V,
        event: MotionEvent
    ): Boolean {

        return if (child == playButton){true}
        else {
            super.onInterceptTouchEvent(parent, child, event)
        }
    }
}