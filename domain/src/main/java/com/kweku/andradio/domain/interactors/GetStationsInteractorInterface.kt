package com.kweku.andradio.domain.interactors

import com.kweku.andradio.domain.models.Station
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

interface GetStationsInteractorInterface {

    fun sendAllStationsToUI():Job
   fun sendPlayableStationToPresentation(station: Station):Job
}