package com.kweku.dependencyinjection


import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.decode.SvgDecoder
import dagger.Module
import dagger.Provides


@Module
class MainModule {



    @Provides
    fun providesImageLoader(context: Context): ImageLoader {

        val errorImageDrawable: Drawable? = ContextCompat
            .getDrawable(context, R.drawable.ic_error_black_24dp)

        val placeHolderImageDrawable: Drawable? = ContextCompat
            .getDrawable(context, R.drawable.placeholder_24dp )

        return ImageLoader(context){
            crossfade(true)
            error(errorImageDrawable)
            placeholder(placeHolderImageDrawable)
            fallback(placeHolderImageDrawable)
            componentRegistry {
                add(SvgDecoder(context))
            }
        }


    }



}