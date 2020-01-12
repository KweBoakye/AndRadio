package com.kweku.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kweku.andradio.domain.PlayOutput
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider


class MainActivityViewModelProviderFactory
@Inject constructor(private val playOutputProvider: Provider<PlayOutput>
)
    : ViewModelProvider.Factory{


@Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val mainActivityViewModel: MainActivityViewModel

        if (modelClass == MainActivityViewModel::class.java) {
            mainActivityViewModel = playOutputProvider.get() as MainActivityViewModel
        }
        else {
            throw RuntimeException("unsupported view model class: $modelClass ")
        }
        return  mainActivityViewModel as T
    }





}