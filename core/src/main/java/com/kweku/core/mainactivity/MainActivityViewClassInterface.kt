package com.kweku.core.mainactivity

import android.view.View
import com.kweku.andradio.domain.models.PlayableStation
import com.kweku.andradio.domain.models.Station

interface MainActivityViewClassInterface {

    interface MainActivityViewClassListener{

    }


    fun getRootView(): View
    fun setStationName(playableStation: PlayableStation)
    fun setStationImage(station: Station)
    fun setPlaybackControlsToPlaying()
    fun setPlaybackControlsToBuffering()
    fun setPlaybackControlsToStopped()

}