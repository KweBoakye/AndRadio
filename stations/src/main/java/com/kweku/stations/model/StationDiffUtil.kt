package com.kweku.stations.model

import androidx.recyclerview.widget.DiffUtil
import com.kweku.andradio.domain.models.*

class StationDiffUtil():DiffUtil.ItemCallback<Station>() {
    override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean {
        return ((oldItem.stationUuid== newItem.stationUuid) and (newItem.stationId == oldItem.stationId))
    }

    override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean {
        return with(newItem){
            (stationId       == oldItem.stationId     )   and
                (changeuuid      == oldItem.changeuuid)  and
                (stationUuid     == oldItem.stationUuid)and
                (stationName     == oldItem.stationName)and
                (url             == oldItem.url) and
                (homepage        == oldItem.homepage)and
                (favicon         == oldItem.favicon)and
                (stationTags     == oldItem.stationTags)and
                (country         == oldItem.country)and
                (state           == oldItem.state)and
                (stationLanguage == oldItem.stationLanguage)and
                (codecType       == oldItem.codecType)and
                (bitrate         == oldItem.bitrate)and
                (working         == oldItem.working)

        }
    }
}