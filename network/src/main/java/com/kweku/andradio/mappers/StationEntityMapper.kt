package com.kweku.andradio.mappers


import com.kweku.andradio.models.StationNetworkEntity
import com.kweku.andradio.domain.models.Station

import com.kweku.andradio.domain.repository.ModelMapper

class StationEntityMapper():ModelMapper<Station, StationNetworkEntity> {

    override fun fromEntity(from: StationNetworkEntity): Station {
        with(from) {
            return Station(
                stationId,
                changeuuid,
                stationUuid,
                stationName,
                url,
                homepage,
                favicon,
                stationTags,
                country,
                state,
                stationLanguage,
                codecType,
                bitrate,
                working
            )
        }
    }

    override fun toEntity(from: Station): StationNetworkEntity {
        with(from) {
            return StationNetworkEntity(
                stationId,
                changeuuid,
                stationUuid,
                stationName,
                url,
                homepage,
                favicon,
                stationTags,
                country,
                state,
                stationLanguage,
                codecType,
                bitrate,
                working
            )
        }
    }
}