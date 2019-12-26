package com.kweku.dependencyinjection


import android.content.Context
import coil.ImageLoader
import dagger.Module
import dagger.Provides


@Module
class MainModule {

    @Provides
    fun providesImageLoader(context: Context): ImageLoader {
        return ImageLoader(context){
            crossfade(true)
            error(R.drawable.ic_error_black_24dp)
            placeholder(R.drawable.placeholder_24dp)


        }
    }


}