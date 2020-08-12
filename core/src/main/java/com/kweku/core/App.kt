package com.kweku.core

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

import coil.ImageLoader
import coil.util.CoilUtils
import com.kweku.dependencyinjection.DaggerMainComponent
import com.kweku.dependencyinjection.MainComponent
import timber.log.Timber


class App:  Application() {

    private lateinit var mainComponent: MainComponent
    fun provideMainComponent():MainComponent = mainComponent


    override fun attachBaseContext(base: Context?) {
        MultiDex.install(this)
        super.attachBaseContext(base)

    }


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        mainComponent = DaggerMainComponent
                .builder()
            .context(this)
                .build()



    }
}