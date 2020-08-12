package com.kweku

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.media.session.MediaButtonReceiver
import com.kweku.radioplayer.R

class NotificationClass(val context: Context, val mediaSession: MediaSessionCompat) {

    private val CHANNEL_ID = "AndRadioChannel"
    val NOTIFICATION_ID = 1

    val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID).apply {
        setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    }

    fun setUpNotification(){
        notificationBuilder.apply {
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

            addAction(
                NotificationCompat.Action(
                    R.drawable.exo_icon_pause,
                    "pause",
                    MediaButtonReceiver.buildMediaButtonPendingIntent(
                        context,
                        PlaybackStateCompat.ACTION_PLAY_PAUSE
                    )
                )
            )

            setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
                    .setShowActionsInCompactView(0))
        }
    }

    fun createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, "AndRadio",
                NotificationManager.IMPORTANCE_LOW )
        }
    }
}