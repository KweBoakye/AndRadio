package com.kweku.andradio.api



import com.kweku.andradio.models.StationClickCountEntity
import com.kweku.andradio.models.StructStation
import retrofit2.http.GET
import retrofit2.http.Path

interface RadioApi {

    @GET("stations")
    suspend fun getAllStations(): List<StructStation>

    @GET("url/{stationId}")
    suspend fun sendStationClick(@Path("stationId")stationID:String):StationClickCountEntity
}