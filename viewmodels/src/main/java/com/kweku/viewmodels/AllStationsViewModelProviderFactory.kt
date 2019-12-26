package com.kweku.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kweku.andradio.domain.AllStationsOutput
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider


class AllStationsViewModelProviderFactory @Inject constructor(private val allStationsOutputProvider: Provider<AllStationsOutput>):ViewModelProvider.Factory {



    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val allStationsViewModel: AllStationsViewModel

        if (modelClass == AllStationsViewModel::class.java) {
            allStationsViewModel = allStationsOutputProvider.get()
        }
        else {
            throw RuntimeException("unsupported view model class: $modelClass ")
        }
      return  allStationsViewModel as T
    }
}