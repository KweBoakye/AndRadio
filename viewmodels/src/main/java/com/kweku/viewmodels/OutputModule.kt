package com.kweku.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.kweku.andradio.domain.AllStationsOutput
import com.kweku.andradio.domain.PlayOutput
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [OutputModule.OutputModuleInterface::class])
object  OutputModule {

    @Provides
    @Singleton
    fun providesAllStationsViewModel():AllStationsViewModel{
        return AllStationsViewModel()
}



    @Module
    interface OutputModuleInterface {
        @Singleton
        @Binds
        fun bindsAllStationsOutput(allStationsViewModel: AllStationsViewModel): AllStationsOutput

        @Binds
        @Singleton
        fun bindsAllStationsViewModelProviderFactory(allStationsViewModelProviderFactory :AllStationsViewModelProviderFactory):ViewModelProvider.Factory

        @Singleton
        @Binds
        fun bindPlayOutout(mainActivityViewModel: MainActivityViewModel): PlayOutput

        @Binds
        fun bindsMainActivityViewModelProviderFactory(mainActivityViewModelProviderFactory: MainActivityViewModelProviderFactory):ViewModelProvider.Factory


    }
}