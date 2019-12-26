package com.kweku.andradio.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StationLanguageEntity(@Json(name = "name")val stationLanguage: String,
                                 @Json(name = "stationcount")val stationCount: Int) {
}