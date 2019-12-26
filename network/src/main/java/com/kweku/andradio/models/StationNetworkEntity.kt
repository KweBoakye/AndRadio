package com.kweku.andradio.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StationNetworkEntity(@Json(name = "id") val stationId: String,
                                @Json(name = "changeuuid")val changeuuid: String,
                                @Json(name = "stationuuid")val stationUuid: String,
                                @Json(name = "name")val stationName: String,
                                @Json(name = "url")val url: String,
                                @Json(name = "homepage")val homepage: String?,
                                @Json(name = "favicon")val favicon:String?,
                                @Json(name = "tags")  val stationTags: String?,
                                @Json(name = "country")  val country: String?,
                                @Json(name = "state")val state: String?,
                                @Json(name = "language")val stationLanguage: String?,
                                @Json(name = "codec")val codecType: String?,
                                @Json(name = "bitrate")val bitrate: Int?,
                                @Json(name = "lastcheckok")val working: String)
               {
}