package com.kweku.viewmodels

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.lifecycle.*
import coil.DefaultRequestOptions
import coil.ImageLoader

import coil.api.get
import coil.api.load
import coil.request.GetRequest
import coil.request.GetRequestBuilder
import coil.request.LoadRequest
import coil.request.LoadRequestBuilder
import com.kweku.andradio.domain.PlayOutput
import com.kweku.andradio.domain.models.StationClickCount
import com.kweku.andradio.domain.models.Station
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject constructor( private val imageLoader:ImageLoader):ViewModel(), PlayOutput {




   private val stationClickCountLiveData: MutableLiveData<StationClickCount> = MutableLiveData()
    private val stationNameLiveData: MutableLiveData<String> = MutableLiveData()
    private val stationIconLiveData: MutableLiveData<Pair<String,Drawable>> = MutableLiveData()
    private val mediaSessionMetaData: MutableLiveData<Pair<String, Drawable>> = MutableLiveData()



    override suspend fun getStationToPlay(
        stationClickCount: StationClickCount,
        station: Station
    )= withContext(Dispatchers.Main) {

        val stationName: String = station.stationName
        val stationIconUrl: String = station.favicon
        Timber.i(stationIconUrl)

        val stationIconDrawableDeferred =
             getStationIconDrawableAsync(stationIconUrl)

        val stationIconDrawable = stationIconDrawableDeferred.await()

        setStationNameLiveData(stationName)
        setStationIconLiveData(stationIconUrl, stationIconDrawable)
        setPlayableStationLiveData(stationClickCount)
        setMediaSessionMetaData(stationName, stationIconDrawable)
    }

    fun setStationNameLiveData(stationName: String) {
        stationNameLiveData.value = stationName
    }

     private fun setPlayableStationLiveData(stationClickCount: StationClickCount){
        stationClickCountLiveData.value = stationClickCount
    }

     fun loadAndSetStationIconLiveData(stationIconUrl: String) = viewModelScope.launch(Dispatchers.IO) {
       val stationIcon = getStationIconDrawableAsync(stationIconUrl).await()
        setStationIconLiveData(stationIconUrl, stationIcon)
    }

    private fun setMediaSessionMetaData(stationName: String, stationIconDrawable: Drawable){
        mediaSessionMetaData.value = Pair(stationName, stationIconDrawable)
    }

   private suspend fun setStationIconLiveData(stationIconUrl: String, stationIcon: Drawable)= withContext(Dispatchers.Main){
        stationIconLiveData.value = Pair(stationIconUrl,stationIcon )
    }

    private suspend fun getStationIconDrawableAsync(stationIconUrl: String): Deferred<Drawable> = viewModelScope.async {

        try {
            if (stationIconUrl == "") {
                return@async imageLoader.defaults.error!!
            } else {
                return@async imageLoader.get(stationIconUrl)
            }
        }

        catch (error: IllegalStateException){
            return@async imageLoader.defaults.error!!
        }

    }


    fun observePlayableStationLiveData(viewLifecycleOwner: LifecycleOwner, observerAction: (StationClickCount) -> Unit ){
        (stationClickCountLiveData as LiveData<StationClickCount>).observe(viewLifecycleOwner,
            Observer<StationClickCount> { playableStation ->
                observerAction(playableStation)})
    }

    fun observeStationNameLiveData(viewLifecycleOwner: LifecycleOwner, observerAction: (String) -> Unit ){
        (stationNameLiveData as LiveData<String>).observe(viewLifecycleOwner,
        Observer<String> { stationName ->
            observerAction(stationName)})
    }

    fun observeStationIconLiveData(viewLifecycleOwner: LifecycleOwner, observerAction: (String, Drawable) -> Unit ){
        (stationIconLiveData as LiveData<Pair<String,Drawable>>).observe(viewLifecycleOwner,
            Observer<Pair<String,Drawable>>{stationIconData ->
                observerAction(stationIconData.first, stationIconData.second)
            })
    }

    fun observeMediaSessionMetaData(viewLifecycleOwner: LifecycleOwner, observerAction: (String, Drawable) -> Unit ){
        (mediaSessionMetaData as LiveData<Pair<String,Drawable>>).observe(viewLifecycleOwner,
            Observer<Pair<String,Drawable>>{mediaSessionMetaData ->
                val stationName:String = mediaSessionMetaData.first
                val stationIconDrawable: Drawable = mediaSessionMetaData.second
                observerAction(stationName, stationIconDrawable)
            })
    }


}


