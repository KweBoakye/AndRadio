package com.kweku.andradio.mappers

import com.kweku.andradio.domain.models.StationClickCount
import com.kweku.andradio.domain.repository.ModelMapper
import com.kweku.andradio.models.StationClickCountEntity

class PlayableStationMapper:ModelMapper<StationClickCount, StationClickCountEntity> {

    override fun fromEntity(from: StationClickCountEntity): StationClickCount {
        return StationClickCount(
            from.statusOk,
            from.message,
            from.stationUuid,
            from.stationName,
            from.playableUrl
        )
    }

    override fun toEntity(from: StationClickCount): StationClickCountEntity {
        return StationClickCountEntity(
            from.statusOk,
            from.message,
            from.stationUuid,
            from.stationName,
            from.playableUrl
        )
    }
}