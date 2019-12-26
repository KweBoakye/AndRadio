package com.kweku.andradio.domain.models

import com.kweku.andradio.domain.models.Country

data class State(val stateName:String,
                 val country: String,
                 val stationCount: Int) {
}