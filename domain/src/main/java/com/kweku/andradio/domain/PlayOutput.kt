package com.kweku.andradio.domain

import com.kweku.andradio.domain.models.StationClickCount
import com.kweku.andradio.domain.models.Station

interface PlayOutput {

    suspend fun getStationToPlay(
        stationClickCount: StationClickCount,
        station: Station
    )

}