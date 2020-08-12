package com.kweku.core.mainactivity


import android.graphics.drawable.Drawable
import android.view.View

interface MainActivityViewClassInterface {

    interface MainActivityViewClassListener{

    }


    fun getRootView(): View
    fun setStationName(stationNameString: String)
    fun setStationImage(drawable: Drawable)
    fun setPlaybackControlsToPlaying()
    fun setPlaybackControlsToBuffering()
    fun setPlaybackControlsToStopped()
    fun setPlayButtonOnClickListener()

}