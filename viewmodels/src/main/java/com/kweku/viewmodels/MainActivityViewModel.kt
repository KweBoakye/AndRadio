package com.kweku.viewmodels

import androidx.lifecycle.*
import com.kweku.andradio.domain.PlayOutput
import com.kweku.andradio.domain.models.PlayableStation
import com.kweku.andradio.domain.models.Station
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivityViewModel @Inject constructor():ViewModel(), PlayOutput {




   private val playableStationLiveData: MutableLiveData<PlayableStation> = MutableLiveData()
    private val stationLiveData: MutableLiveData<Station> = MutableLiveData()

    fun getPlayableStationLiveData():LiveData<PlayableStation> = playableStationLiveData

    override suspend fun getStationToPlay(
        playableStation: PlayableStation,
        station: Station
    )= withContext(Dispatchers.Main) {
        setStationLiveData(station)
        setPlayableStationLiveData(playableStation)
    }

      private fun setStationLiveData(station: Station) {
        stationLiveData.value = station
    }

     private fun setPlayableStationLiveData(playableStation: PlayableStation){
        playableStationLiveData.value = playableStation
    }

    fun observePlayableStationLiveData(viewLifecycleOwner: LifecycleOwner, observerAction: (PlayableStation) -> Unit ){
        (playableStationLiveData as LiveData<PlayableStation>).observe(viewLifecycleOwner,
            Observer<PlayableStation> { playableStation ->
                observerAction(playableStation)})
    }

    fun observeStationLiveData(viewLifecycleOwner: LifecycleOwner, observerAction: (Station) -> Unit ){
        (stationLiveData as LiveData<Station>).observe(viewLifecycleOwner,
        Observer<Station> { station ->
            observerAction(station)})
    }


}


