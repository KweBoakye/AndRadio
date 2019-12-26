package com.kweku.andradio.mappers

import com.kweku.andradio.domain.models.PlayableStation
import com.kweku.andradio.domain.repository.ModelMapper
import com.kweku.andradio.models.PlayableStationEntity

class PlayableStationMapper:ModelMapper<PlayableStation, PlayableStationEntity> {

    override fun fromEntity(from: PlayableStationEntity): PlayableStation {
        return PlayableStation(
            from.statusOk,
            from.message,
            from.stationID,
            from.stationName,
            from.playableUrl
        )
    }

    override fun toEntity(from: PlayableStation): PlayableStationEntity {
        return PlayableStationEntity(
            from.statusOk,
            from.message,
            from.stationID,
            from.stationName,
            from.playableUrl
        )
    }
}