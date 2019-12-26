package com.kweku.stations

import android.view.View
import com.kweku.andradio.domain.models.Station

interface AllStationsViewClassInterface {

    interface AllStationsViewClassListener {
        fun playClickedStation(station: Station)
    }

    fun getRoot(): View
    fun setRecyclerData(stations :List<Station>)

}