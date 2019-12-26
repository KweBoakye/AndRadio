package com.kweku.andradio.domain.interactors

import com.kweku.andradio.domain.AllStationsOutput
import com.kweku.andradio.domain.PlayOutput
import com.kweku.andradio.domain.models.PlayableStation
import com.kweku.andradio.domain.models.Station
import com.kweku.andradio.domain.repository.RepositoryInterface
import kotlinx.coroutines.*


class GetStationsInteractor (private val repositoryInterface: RepositoryInterface,
                            private val allStationsOutput: AllStationsOutput,
                             private val playOutput: PlayOutput):GetStationsInteractorInterface {

    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + parentJob )

     private fun getAllStationsAsync():Deferred<List<Station>> = coroutineScope.async{
         return@async repositoryInterface.getAllRadioStations() }

     override fun sendAllStationsToUI() = coroutineScope.launch{
        allStationsOutput.updateAllStationsLiveData(getAllStationsAsync().await())
    }


    private fun getPlayableStationAsync(stationId: String):Deferred<PlayableStation> = coroutineScope.async {
        return@async repositoryInterface.getPlayableStation(stationId)
    }

    override fun sendPlayableStationToPresentation(station: Station):Job = coroutineScope.launch {
        playOutput.getStationToPlay(
            getPlayableStationAsync(station.stationId).await(),
            station
        )
    }



}


