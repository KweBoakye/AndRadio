package com.kweku.core.mainactivity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import coil.api.load
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.kweku.andradio.domain.PlayOutput
import com.kweku.andradio.domain.models.PlayableStation
import com.kweku.andradio.domain.models.Station
import com.kweku.core.App
import com.kweku.core.R
import com.kweku.core.databinding.ActivityMainBinding
import com.kweku.core.getViewModel
import com.kweku.dependencyinjection.MainComponent
import com.kweku.radioplayer.RadioService
import com.kweku.viewmodels.MainActivityViewModel
import com.kweku.viewmodels.MainActivityViewModelProviderFactory
import javax.inject.Provider

class MainActivity : AppCompatActivity(), Player.EventListener {

    lateinit var playableUrlIntent:Intent
    lateinit var  binding: ActivityMainBinding
    lateinit var  player: SimpleExoPlayer
    private var isRadioServiceBound: Boolean = false
    lateinit var binder: RadioService.RadioServiceBinder
    lateinit var playOutput: Provider<PlayOutput>
    lateinit var mainActivityViewClassInterface: MainActivityViewClassInterface

    private lateinit var mainActivityViewModelProviderFactory: MainActivityViewModelProviderFactory

    lateinit var mainActivityViewModel: MainActivityViewModel

    private val radioServiceonnection:ServiceConnection = object: ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                isRadioServiceBound = false
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                if (service is RadioService.RadioServiceBinder){
                    service.getPlayerInstance().addListener(this@MainActivity)
                    binder = service
                }
                isRadioServiceBound = true
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainComponent: MainComponent = (applicationContext as App)
            .provideMainComponent()

        mainActivityViewModelProviderFactory = mainComponent
            .getMainAcivityViewModelProviderFactory()

        mainActivityViewModel = this.getViewModel(mainActivityViewModelProviderFactory)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val root: View = binding.root

         mainActivityViewClassInterface =
            MainActivityViewClass(this, playPauseAction())

        setContentView(mainActivityViewClassInterface.getRootView())
            }

    override fun onStart() {
        super.onStart()
        playableUrlIntent = Intent(applicationContext, RadioService::class.java)

        bindService(playableUrlIntent, radioServiceonnection, Context.BIND_AUTO_CREATE)

        mainActivityViewModel.observePlayableStationLiveData(this, this::playRadioStation)
        mainActivityViewModel.observeStationLiveData(this, this::setStation)
    }

    override fun onStop() {
        super.onStop()

        unbindService(radioServiceonnection)
        isRadioServiceBound = false
    }

    private fun playPauseAction(){
        startService(Intent(this, RadioService::class.java).apply {
            putExtra(RadioService.PLAY_PAUSE_ACTION,0)
        })
    }

    private fun playRadioStation(playableStation: PlayableStation){
        mainActivityViewClassInterface.setStationName(playableStation)
        binder.startPlayback(playableStation.playableUrl)
    }

    private fun setStation(station: Station){
        mainActivityViewClassInterface.setStationImage(station)
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
