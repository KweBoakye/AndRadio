package com.kweku.andradio.mappers

import com.kweku.andradio.domain.models.Station
import com.kweku.andradio.domain.repository.ModelMapper
import com.kweku.andradio.models.StructStation

class StructStationToStationMapper: ModelMapper<Station, StructStation> {
    override fun fromEntity(from: StructStation): Station {
        with(from){
      return  Station(
              "",
              changeUuid,
              stationUuid,
              stationName,
              streamUrl,
              homepageUrl,
              faviconUrl,
              tags,
              country,
              state,
              language,
              codec,
              bitrate,
              lastCheckOk.toString()
       )
        }
    }

    override fun toEntity(from: Station): StructStation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}