package com.kweku.andradio.mappers

import com.kweku.andradio.models.StationLanguageEntity
import com.kweku.andradio.domain.models.StationLanguage
import com.kweku.andradio.domain.repository.ModelMapper

class StationLanguageEntityMapper:ModelMapper<StationLanguage, StationLanguageEntity> {
    override fun fromEntity(from: StationLanguageEntity): StationLanguage {
        return StationLanguage(from.stationLanguage,
            from.stationCount)
    }

    override fun toEntity(from: StationLanguage): StationLanguageEntity {
        return StationLanguageEntity(
            from.stationLanguage,
            from.stationCount
        )
    }
}