package com.kweku.andradio.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class StructStation(@Json(name = "changeuuid") val changeUuid:String,
                         @Json(name = "stationuuid") val stationUuid: String,
                         @Json(name = "name") val stationName: String,
                         @Json(name = "url") val streamUrl: String,
                         @Json(name = "url_resolved") val resolvedStreamUrl: String,
                         @Json(name = "homepage") val homepageUrl: String,
                         @Json(name = "favicon") val faviconUrl: String,
                         @Json(name = "tags") val tags: String,
                         @Json(name = "country") val country: String,
                         @Json(name = "countrycode") val countryCode: String,
                         @Json(name = "state") val state: String,
                         @Json(name = "language") val language: String,
                         @Json(name = "votes") val votes: Int,
                         @Json(name = "lastchangetime") val lastChangeTime: String,
                         @Json(name = "codec") val codec: String,
                         @Json(name = "bitrate") val bitrate: Int,
                         @Json(name = "hls") val hls: Int,
                         @Json(name = "lastcheckok") val lastCheckOk: Int,
                         @Json(name = "lastchecktime") val lastCheckTime: String,
                         @Json(name = "lastcheckoktime") val lastCheckOkTime:String,
                         @Json(name = "lastlocalchecktime") val lastLocalCheckTime: String,
                         @Json(name = "clicktimestamp") val clickTimeStamp: String,
                         @Json(name = "clickcount") val clickCount: Int,
                         @Json(name = "clicktrend") val clickTrend: Int) {
}