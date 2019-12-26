package com.kweku.andradio.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CodecTypeEntity(@Json(name = "name")val codec: String,
                           @Json(name = "value")val stationCount:Int) {
}