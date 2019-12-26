package com.kweku.stations.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kweku.viewmodels.AllStationsViewModel
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

class StationViewModelFactory @Inject constructor(private val allStationsViewModelProvider: Provider<AllStationsViewModel>): ViewModelProvider.Factory{





    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: ViewModel
        if(modelClass == AllStationsViewModel::class.java){
            viewModel = allStationsViewModelProvider.get()
        }
        else{
            throw RuntimeException("unsupported view model class: $modelClass")
        }
        return viewModel as T
    }
}