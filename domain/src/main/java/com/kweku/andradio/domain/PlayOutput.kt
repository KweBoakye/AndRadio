package com.kweku.andradio.domain

import com.kweku.andradio.domain.models.PlayableStation
import com.kweku.andradio.domain.models.Station

interface PlayOutput {

    suspend fun getStationToPlay(
        playableStation: PlayableStation,
        station: Station
    )

}