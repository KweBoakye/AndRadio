package com.kweku.andradio.domain.models




data class Station(val stationId: String,
                   val changeuuid: String,
                   val stationUuid: String,
                   val stationName: String,
                   val url: String,
                   val homepage: String?,
                   val favicon:String,
                   val stationTags: String?,
                   val country: String?,
                   val state: String?,
                   val stationLanguage: String?,
                   val codecType: String?,
                   val bitrate: Int?,
                   val working: String)
               {
}