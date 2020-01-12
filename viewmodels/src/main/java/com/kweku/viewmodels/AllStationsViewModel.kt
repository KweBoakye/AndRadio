package com.kweku.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.kweku.andradio.domain.AllStationsOutput
import com.kweku.andradio.domain.models.Station

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton



class AllStationsViewModel @Inject constructor():ViewModel(), AllStationsOutput {

   private val allStationsLiveData:MutableLiveData<List<Station>> = MutableLiveData()


    override suspend fun updateAllStationsLiveData(stations: List<Station>)= withContext(Dispatchers.Main){
        allStationsLiveData.value = stations
        Timber.i("${allStationsLiveData.value}")
        Timber.i("${allStationsLiveData.hasActiveObservers()}")
    }

    fun getAllStationsLiveData():LiveData<List<Station>> = allStationsLiveData

    fun observeAllStationsLiveData(viewLifecycleOwner: LifecycleOwner, observerAction: (List<Station>) -> Unit ){
        (allStationsLiveData as LiveData<List<Station>>).observe(viewLifecycleOwner,
            Observer<List<Station>> { stations ->
                observerAction(stations)})
    }



}