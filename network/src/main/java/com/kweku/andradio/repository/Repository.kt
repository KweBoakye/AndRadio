package com.kweku.andradio.repository

import com.kweku.andradio.api.RadioApi
import com.kweku.andradio.domain.models.StationClickCount
import com.kweku.andradio.domain.models.Station
import com.kweku.andradio.domain.repository.RepositoryInterface
import com.kweku.andradio.mappers.PlayableStationMapper
import com.kweku.andradio.mappers.StationEntityMapper
import com.kweku.andradio.mappers.StructStationToStationMapper
import com.kweku.andradio.network.RetrofitClientInstance
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(): RepositoryInterface {

   private val networkModelMapper = StationEntityMapper()
    private val playableStationMapper = PlayableStationMapper()
    private val structStationToStationMapper = StructStationToStationMapper()

    private val radioApiService: RadioApi = RetrofitClientInstance.retrofit.create(RadioApi::class.java)

    override suspend fun getAllRadioStations(): List<Station> {
        return radioApiService.getAllStations()
            .map { structStationToStationMapper.fromEntity(it)

            }
    }


    override suspend fun sendStationClick(stationId: String): StationClickCount{
        return playableStationMapper
            .fromEntity(radioApiService.sendStationClick(stationId))
    }
}