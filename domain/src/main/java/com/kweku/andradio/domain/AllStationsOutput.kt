package com.kweku.andradio.domain

import com.kweku.andradio.domain.models.Station

interface AllStationsOutput {

    suspend fun updateAllStationsLiveData(stations: List<Station>)


}