package com.kweku.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner


inline fun <reified T: ViewModel> ViewModelStoreOwner.getViewModel(viewModelProviderFactory: ViewModelProvider.Factory):T{
    return  ViewModelProvider(this, viewModelProviderFactory).get(T::class.java)
}