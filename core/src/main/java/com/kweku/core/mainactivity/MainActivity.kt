package com.kweku.core.mainactivity

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.core.content.ContextCompat
import coil.ImageLoader
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.kweku.andradio.domain.models.StationClickCount

import com.kweku.core.App
import com.kweku.core.getViewModel
import com.kweku.dependencyinjection.MainComponent
import com.kweku.radioplayer.RadioService
import com.kweku.viewmodels.MainActivityViewModel
import com.kweku.viewmodels.MainActivityViewModelProviderFactory
import timber.log.Timber

class MainActivity : AppCompatActivity(), Player.EventListener {

    lateinit var radioPlayerIntent:Intent
    lateinit var notificationIntent:Intent
    lateinit var  player: SimpleExoPlayer
    private var isRadioServiceBound: Boolean = false
    lateinit var binder: RadioService.RadioServiceBinder
    private var stationPlayed = false
    lateinit var mainActivityViewClassInterface: MainActivityViewClassInterface



    private lateinit var mainActivityViewModelProviderFactory: MainActivityViewModelProviderFactory

    lateinit var mainActivityViewModel: MainActivityViewModel

    lateinit var imageLoader: ImageLoader

    private val radioServiceConnection:ServiceConnection = object: ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                isRadioServiceBound = false
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                if (service is RadioService.RadioServiceBinder){
                    service
                        .getPlayerInstance()
                        .addListener(this@MainActivity)
                    binder = service

                    mainActivityViewModel.run {
                        observeMediaSessionMetaData(this@MainActivity,
                            binder::setStationAndMediaSessionMetaData)

                        if (binder.serviceHasPreviouslyBeenUnbound() and stationPlayed){
                            setStationNameLiveData(binder.getCurrentStationName())
                            loadAndSetStationIconLiveData(binder.getCurrentStationIconUrl())
                        }
                    }
                }
                isRadioServiceBound = true
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainComponent: MainComponent = (applicationContext as App).provideMainComponent()
        mainActivityViewModelProviderFactory = mainComponent.getMainAcivityViewModelProviderFactory()
        imageLoader = mainComponent.getImageLoader()

        mainActivityViewModel = this.getViewModel(mainActivityViewModelProviderFactory)

         mainActivityViewClassInterface =
            MainActivityViewClass(this, ::playPauseAction)

        radioPlayerIntent = Intent(applicationContext, RadioService::class.java)
        val activityIntent = Intent(applicationContext, MainActivity::class.java)
        notificationIntent = radioPlayerIntent.putExtra("activityIntent",activityIntent)

        ContextCompat.startForegroundService(this, notificationIntent)
        setContentView(mainActivityViewClassInterface.getRootView())
            }

    override fun onStart() {
        super.onStart()


        mainActivityViewClassInterface.setPlayButtonOnClickListener()

        bindService(radioPlayerIntent, radioServiceConnection, Context.BIND_AUTO_CREATE)

        mainActivityViewModel.run {
            observePlayableStationLiveData(this@MainActivity, ::playRadioStation)
        observeStationNameLiveData(this@MainActivity, ::setStation)
        observeStationIconLiveData(this@MainActivity, ::setStationIcon)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(radioServiceConnection)
    }

    private fun playPauseAction(){
        Timber.i("playPause")
              binder.playPause()
    }

    private fun playRadioStation(stationClickCount: StationClickCount){
        binder.startPlayback(stationClickCount.playableUrl)
        stationPlayed = true
    }

    private fun setStation(stationNameString: String){
        mainActivityViewClassInterface.setStationName(stationNameString)
        binder.setCurrentStationName(stationNameString)
    }

    private fun setStationIcon(stationIconUrlString: String, stationIcon: Drawable){
        mainActivityViewClassInterface.setStationImage(stationIcon)
        binder.setCurrentStationIconUrl(stationIconUrlString)
        binder.setStationAndMediaSessionMetaData(stationIconUrlString, stationIcon)
    }



    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        with(mainActivityViewClassInterface) {
            if (playWhenReady && playbackState == Player.STATE_READY) {
                setPlaybackControlsToPlaying()
            } else if (playbackState == Player.STATE_BUFFERING) {
                setPlaybackControlsToBuffering()
            } else {
                setPlaybackControlsToStopped()
            }
        }
    }

}
