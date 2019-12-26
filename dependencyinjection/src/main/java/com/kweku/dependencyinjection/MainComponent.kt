package com.kweku.dependencyinjection

import android.app.Application
import android.content.Context
import coil.ImageLoader
import com.kweku.andradio.domain.AllStationsOutput
import com.kweku.andradio.domain.PlayOutput
import com.kweku.andradio.domain.interactors.GetStationsInteractorInterface
import com.kweku.andradio.domain.repository.RepositoryInterface
import com.kweku.viewmodels.MainActivityViewModel
import com.kweku.viewmodels.MainActivityViewModelProviderFactory
import com.kweku.viewmodels.OutputModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GetStationsModule::class, MainModule::class, NetworkModule::class, OutputModule::class])
interface MainComponent {

    fun getGetStationsInteractorInterface(): GetStationsInteractorInterface

    fun getRepositoryInterface(): RepositoryInterface

    fun getAlStationsOutput():AllStationsOutput

    fun getPlayOutput():PlayOutput

    fun getMainActivityViewModel():MainActivityViewModel

    fun getMainAcivityViewModelProviderFactory(): MainActivityViewModelProviderFactory

    fun getImageLoader():ImageLoader

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context): Builder

        fun build():MainComponent
    }




}