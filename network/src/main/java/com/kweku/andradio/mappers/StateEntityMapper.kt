package com.kweku.andradio.mappers

import com.kweku.andradio.models.StateEntity
import com.kweku.andradio.domain.repository.ModelMapper
import com.kweku.andradio.domain.models.State
import com.kweku.andradio.mappers.CountryEntityMapper

class StateEntityMapper(private val countryEntityMapper: CountryEntityMapper) :ModelMapper<State, StateEntity>{
    override fun fromEntity(from: StateEntity): State {
        return State(from.stateName,
            from.country,
            from.stationCount)
    }

    override fun toEntity(from: State): StateEntity {
        return StateEntity(
            from.stateName,
            from.country,
            from.stationCount
        )
    }

}