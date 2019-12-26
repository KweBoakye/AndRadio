package com.kweku.andradio.mappers

import com.kweku.andradio.models.StationTagEntity
import com.kweku.andradio.domain.models.StationTag
import com.kweku.andradio.domain.repository.ModelMapper

class StationTagEntityMapper:ModelMapper<StationTag, StationTagEntity> {
    override fun fromEntity(from: StationTagEntity): StationTag {
        return StationTag(from.stationTag,
            from.stationCount)
    }

    override fun toEntity(from: StationTag): StationTagEntity {
        return StationTagEntity(
            from.stationTag,
            from.stationCount
        )
    }
}