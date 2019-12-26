package com.kweku.dependencyinjection

import com.kweku.andradio.domain.AllStationsOutput
import com.kweku.andradio.domain.PlayOutput
import com.kweku.andradio.domain.interactors.GetStationsInteractor
import com.kweku.andradio.domain.interactors.GetStationsInteractorInterface
import com.kweku.andradio.domain.repository.RepositoryInterface
import dagger.Module
import dagger.Provides

@Module
object GetStationsModule {

    @Provides
    fun providesGetStationsInteractorInterface(getStationsInteractor: GetStationsInteractor): GetStationsInteractorInterface {
        return getStationsInteractor
    }

    @Provides
    fun providesGetStationsInteractor(repositoryInterface: RepositoryInterface,allStationsOutput: AllStationsOutput, playOutput: PlayOutput):GetStationsInteractor{
        return GetStationsInteractor(repositoryInterface,allStationsOutput, playOutput)
    }
}