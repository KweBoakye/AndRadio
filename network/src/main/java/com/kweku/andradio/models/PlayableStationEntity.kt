package com.kweku.andradio.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayableStationEntity(@Json(name = "ok")val statusOk:Boolean,
                                 @Json(name = "message")val message:String,
                                 @Json(name = "id")val stationID: String,
                                 @Json(name = "name")val stationName:String,
                                 @Json(name = "url")val playableUrl: String) {
}