package com.kweku.andradio.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClientInstance {


    companion object {
        val baseRadioUrl = "https://de1.api.radio-browser.info/json/"
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseRadioUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}