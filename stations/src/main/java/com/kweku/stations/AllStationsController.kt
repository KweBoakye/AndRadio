package com.kweku.stations

import com.kweku.andradio.domain.interactors.GetStationsInteractorInterface
import com.kweku.andradio.domain.models.Station
import javax.inject.Inject

class AllStationsController @Inject constructor(
    private val getStationsInteractorInterface: GetStationsInteractorInterface)
    :  AllStationsViewClassInterface.AllStationsViewClassListener {

    fun sendAllStationsToUI()  = getStationsInteractorInterface.sendAllStationsToUI()

   override fun playClickedStation(station: Station){
        getStationsInteractorInterface.sendPlayableStationToPresentation(station)
    }

}