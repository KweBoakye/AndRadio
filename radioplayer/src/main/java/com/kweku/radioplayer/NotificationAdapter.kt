package com.kweku.radioplayer

import android.app.PendingIntent
import android.graphics.Bitmap
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class NotificationAdapter(
    private val getStationName: () -> Deferred<String>,
    private val getStationText: () -> String,
    private val getPendingIntent: () -> PendingIntent,
    private val getStationIcon: () -> Bitmap): PlayerNotificationManager.MediaDescriptionAdapter {


    override fun createCurrentContentIntent(player: Player): PendingIntent? {
       return getPendingIntent.invoke()
    }

    override fun getCurrentContentText(player: Player): String? {
        return getStationText.invoke()
    }

    override fun getCurrentContentTitle(player: Player): String {
        return runBlocking {  getStationName.invoke().await()}
    }

    override fun getCurrentLargeIcon(
        player: Player,
        callback: PlayerNotificationManager.BitmapCallback
    ): Bitmap? {
        return getStationIcon.invoke()
    }
}