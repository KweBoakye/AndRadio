package com.kweku.dependencyinjection

import com.kweku.andradio.repository.Repository
import com.kweku.andradio.domain.repository.RepositoryInterface
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkModule {

    @Binds
    abstract fun providesRepository(repository: Repository): RepositoryInterface



}