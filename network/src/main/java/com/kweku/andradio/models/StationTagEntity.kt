package com.kweku.andradio.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StationTagEntity(@Json(name = "name")val stationTag: String,
                            @Json(name = "stationcount")val stationCount: Int) {
}