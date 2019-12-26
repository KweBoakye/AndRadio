package com.kweku.andradio.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StateEntity(@Json(name = "name")val stateName:String,
                       @Json(name = "country") internal val country: String,
                       @Json(name = "stationcount")val stationCount: Int) {
}