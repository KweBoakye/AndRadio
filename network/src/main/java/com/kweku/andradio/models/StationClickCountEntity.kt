package com.kweku.andradio.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StationClickCountEntity(@Json(name = "ok")val statusOk:Boolean,
                                   @Json(name = "message")val message:String,
                                   @Json(name = "stationuuid")val stationUuid: String,
                                   @Json(name = "name")val stationName:String,
                                   @Json(name = "url")val playableUrl: String) {
}