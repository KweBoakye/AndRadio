package com.kweku.andradio.repository

import com.kweku.andradio.api.RadioApi
import com.kweku.andradio.domain.models.PlayableStation
import com.kweku.andradio.domain.models.Station
import com.kweku.andradio.domain.repository.RepositoryInterface
import com.kweku.andradio.mappers.PlayableStationMapper
import com.kweku.andradio.mappers.StationEntityMapper
import com.kweku.andradio.network.RetrofitClientInstance
import javax.inject.Inject

class Repository @Inject constructor(): RepositoryInterface {

   private val networkModelMapper = StationEntityMapper()
    private val playableStationMapper = PlayableStationMapper()

    private val radioApiService: RadioApi = RetrofitClientInstance.retrofit.create(RadioApi::class.java)

    override suspend fun getAllRadioStations(): List<Station> {
        return radioApiService.getAllStations()
            .map { networkModelMapper.fromEntity(it) }
    }


    override suspend fun getPlayableStation(stationId: String): PlayableStation{
        return playableStationMapper
            .fromEntity(radioApiService.getPlayableUrl(stationId))
    }
}