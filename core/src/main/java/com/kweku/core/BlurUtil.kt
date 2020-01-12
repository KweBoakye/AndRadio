package com.kweku.core

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

class BlurUtil {


    companion object{

        fun getBitmapFromView(view: View):Bitmap{
            with(view){
            return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).also {bitmap ->
                val canvas = Canvas(bitmap)
                layout(left, top, right, bottom )
                draw(canvas)
            }
            }
        }
    }
}