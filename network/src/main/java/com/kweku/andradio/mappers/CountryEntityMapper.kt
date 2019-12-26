package com.kweku.andradio.mappers

import com.kweku.andradio.models.CountryEntity
import com.kweku.andradio.domain.models.Country
import com.kweku.andradio.domain.repository.ModelMapper

class CountryEntityMapper:ModelMapper<Country, CountryEntity> {
    override fun fromEntity(from: CountryEntity): Country {
        return Country(from.country,
            from.stationCount)
    }

    override fun toEntity(from: Country): CountryEntity {
        return CountryEntity(
            from.country,
            from.stationCount
        )
    }
}