package com.kweku.radioplayer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.MediaMetadataCompat.METADATA_KEY_ARTIST
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.media.app.NotificationCompat.MediaStyle
import androidx.media.session.MediaButtonReceiver
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext


class RadioService() : Service() {

    companion object {
        const val PLAYABLE_URL = "PlayableUrl"
        const val PLAY_PAUSE_ACTION = "playPauseAction"
    }

    // private val playerHolder:PlayerHolder = PlayerHolder(this)
    private lateinit var player: SimpleExoPlayer //= ExoPlayerFactory.newSimpleInstance(radioService)
    lateinit var playerNotificationManager: PlayerNotificationManager
    private var currentStationName: String = ""
    private var currentStationIconUrl: String = ""
    private var currentWindow: Int = 0
    private var playBackPosition: Long = 0
    private var currentlyPlayingUrl: String = ""
    private val channelName: String = "AndRadio"
    val CHANNEL_ID = "AndRadioChannel"
    val NOTIFICATION_ID = 1
    val MEDIASESSION_TAG = "AndRadio Media Session"
    private var serviceHasPreviouslyBeenUnbound = false
    private lateinit var notificationManager: NotificationManager


    val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
    val radioServiceCoroutineScope: CoroutineScope = CoroutineScope(coroutineContext)
    val jobToUdateCurrentlyPlayingStation: Job = Job()
    val jobToPassStationInfoToNotifications: Job = Job()
    val updateNotificationDataScope: CoroutineScope = CoroutineScope(
        Dispatchers.Default + jobToPassStationInfoToNotifications
    )


    private lateinit var stationIcon: Bitmap

    lateinit var mediaSession: MediaSessionCompat
    val playbackStateBuilder: PlaybackStateCompat.Builder = PlaybackStateCompat.Builder()
    val mediaMetadataBuilder: MediaMetadataCompat.Builder = MediaMetadataCompat.Builder()
    val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
        setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

    }

    fun setUpNotification(pendingIntent: PendingIntent): NotificationCompat.Builder {

       return notificationBuilder.apply {
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
           setContentTitle("Radio Service")


            addAction(
                NotificationCompat.Action(
                    R.drawable.exo_icon_pause,
                    "pause",
                    MediaButtonReceiver.buildMediaButtonPendingIntent(
                        this@RadioService,
                        PlaybackStateCompat.ACTION_PLAY_PAUSE
                    )
                )
            )

            setStyle(
                MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
                    .setShowActionsInCompactView(0)
            )

            setContentIntent(pendingIntent)
        }


    }

    override fun onCreate() {
        super.onCreate()
        player = SimpleExoPlayer.Builder(this).build()
        mediaSession = MediaSessionCompat(this, MEDIASESSION_TAG)
       createNotificationChannel()



    }




    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.i("startCommand")
        intent?.let { intent ->
            val notificationIntent: Intent? = intent.getParcelableExtra("activityIntent")
            val pendingIntent: PendingIntent = PendingIntent .getActivity(applicationContext, 0, notificationIntent, 0)
            setUpNotification(pendingIntent)
        }

        val notification = notificationBuilder.build()
            startForeground(NOTIFICATION_ID, notification )

        return START_NOT_STICKY
    }


    override fun onBind(intent: Intent?): IBinder? {
        return RadioServiceBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        serviceHasPreviouslyBeenUnbound = true
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun playPause() {
        player.playWhenReady = !player.playWhenReady
        if (player.playWhenReady) player.seekTo(0L)
    }


    inner class RadioServiceBinder : Binder() {
        fun getPlayerInstance() = player

        fun getService(): RadioService = this@RadioService

        fun startPlayback(url: String) = this@RadioService.startPlayback(url)

        fun playPause() = this@RadioService.playPause()

        fun getCurrentStationName(): String = this@RadioService.currentStationName

        fun setCurrentStationName(stationNameString: String) {
            this@RadioService.currentStationName = stationNameString
        }

        fun getCurrentStationIconUrl(): String = this@RadioService.currentStationIconUrl
        fun setCurrentStationIconUrl(stationIconUrl: String) {
            this@RadioService.currentStationIconUrl = stationIconUrl
        }

        fun serviceHasPreviouslyBeenUnbound(): Boolean =
            this@RadioService.serviceHasPreviouslyBeenUnbound

        fun setStationAndMediaSessionMetaData(stationName: String,
                                              stationIconDrawable: Drawable) =
            this@RadioService.setMediaMetadata(stationName, stationIconDrawable)

        fun getNotificationBuilder(pendingIntent: PendingIntent): NotificationCompat.Builder =
            this@RadioService.setUpNotification(pendingIntent)


    }


    fun startPlayback(url: String) {
        Timber.i("play")
        if (url != currentlyPlayingUrl){
            currentlyPlayingUrl = url
        val audioUri: Uri = Uri.parse(url)
        val mediaSource = buildMediaSource(audioUri)
        with(player) {
            playWhenReady = true
            seekTo(currentWindow, playBackPosition)
            prepare(mediaSource, false, false)
        }
        }
    }


    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(this, "radio-app")
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }


    fun setMediaMetadata(stationName: String, stationIconDrawable: Drawable) {

        val stationIconBitmap = stationIconDrawable.toBitmap()
        val mediaMetadata = mediaMetadataBuilder.putBitmap(
            MediaMetadataCompat.METADATA_KEY_ALBUM_ART,
            stationIconBitmap
        )
            .putString(METADATA_KEY_ARTIST, stationName)
            .build()
        mediaSession.setMetadata(mediaMetadata)
    }


    fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                CHANNEL_ID, "AndRadio",
                NotificationManager.IMPORTANCE_LOW
            )

            notificationManager.createNotificationChannel(channel)
        }
    }


}