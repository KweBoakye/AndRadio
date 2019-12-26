package com.kweku.andradio.domain.repository

import com.kweku.andradio.domain.models.PlayableStation
import com.kweku.andradio.domain.models.Station

interface RepositoryInterface {

    suspend fun getAllRadioStations():List<Station>

    suspend fun getPlayableStation(stationId: String): PlayableStation
}