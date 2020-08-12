package com.kweku.stations


import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader

import com.kweku.andradio.domain.interactors.GetStationsInteractorInterface
import com.kweku.andradio.domain.models.Station
import com.kweku.core.App
import com.kweku.dependencyinjection.MainComponent

import com.kweku.viewmodels.AllStationsViewModel
import com.kweku.viewmodels.AllStationsViewModelProviderFactory

import com.kweku.stations.di.DaggerAllStationsComponent
import com.kweku.stations.model.StationViewModelFactory
import timber.log.Timber
import javax.inject.Inject


class AllStationsFragment : Fragment() {


    @Inject
    lateinit var allStationsController: AllStationsController

    @Inject
    lateinit var allStationsViewModelProviderFactory: AllStationsViewModelProviderFactory

    lateinit var allStationsViewModel: AllStationsViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainComponent: MainComponent = (context?.applicationContext as App)
            .provideMainComponent()


        DaggerAllStationsComponent
            .builder()
            .mainComponent(mainComponent)
            .build()
            .inject(this)

     allStationsViewModel =  ViewModelProvider(this, allStationsViewModelProviderFactory)
         .get(AllStationsViewModel::class.java)

        allStationsController.sendAllStationsToUI()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val allStationsViewClassInterface:AllStationsViewClassInterface = AllStationsViewClass(inflater, container, context!!, allStationsController)

        allStationsViewModel.observeAllStationsLiveData(viewLifecycleOwner,allStationsViewClassInterface::setRecyclerData)
        return allStationsViewClassInterface.getRoot()
    }









}
