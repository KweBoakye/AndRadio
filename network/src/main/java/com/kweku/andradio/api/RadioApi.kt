package com.kweku.andradio.api

import com.kweku.andradio.models.PlayableStationEntity
import com.kweku.andradio.models.StationNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface RadioApi {

    @GET("stations")
    suspend fun getAllStations(): List<StationNetworkEntity>

    @GET("url/{stationId}")
    suspend fun getPlayableUrl(@Path("stationId")stationID:String):PlayableStationEntity
}