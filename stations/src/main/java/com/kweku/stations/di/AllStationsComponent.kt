package com.kweku.stations.di

import com.kweku.dependencyinjection.MainComponent
import com.kweku.stations.AllStationsFragment
import dagger.Component
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention
annotation class StationScope

@StationScope
@Component(modules = [AllStationsModule::class], dependencies = [MainComponent::class])
interface AllStationsComponent {

    fun inject(allStationsFragment:AllStationsFragment)
}