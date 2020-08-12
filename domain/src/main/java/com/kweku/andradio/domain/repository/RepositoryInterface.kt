package com.kweku.andradio.domain.repository


import com.kweku.andradio.domain.models.Station
import com.kweku.andradio.domain.models.StationClickCount

interface RepositoryInterface {

    suspend fun getAllRadioStations():List<Station>

    suspend fun sendStationClick(stationId: String): StationClickCount
}