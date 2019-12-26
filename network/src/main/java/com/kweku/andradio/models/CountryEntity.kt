package com.kweku.andradio.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryEntity(@Json(name = "name")val country: String,
                         @Json(name = "stationcount")val stationCount:Int) {
}