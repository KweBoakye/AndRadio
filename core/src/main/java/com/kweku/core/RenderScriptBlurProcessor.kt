package com.kweku.core

import android.graphics.Bitmap
import android.renderscript.*

class RenderScriptBlurProcessor(private val renderScript: RenderScript) {

    companion object{
        const val MAX_RADIUS:Float = 25f // max supported Radius
    }

    fun blur(bitmap: Bitmap, radius: Float, repeat:Int): Bitmap{

        val restrictedRadius: Float = if (radius > MAX_RADIUS){
            MAX_RADIUS
        } else {
            radius
        }

        val bitmapType: Type = Type.Builder(renderScript,
            Element.RGBA_8888(renderScript))
            .setX(bitmap.width)
            .setY(bitmap.height)
            .setMipmaps(false)
            .create()
        var allocation = Allocation.createTyped(renderScript, bitmapType)

        var scriptIntrinsicBlur = ScriptIntrinsicBlur
            .create(renderScript, Element.U8(renderScript))
            .also { scriptIntrinsicBlur ->
                scriptIntrinsicBlur.setRadius(restrictedRadius)
                allocation.copyFrom(bitmap)
                scriptIntrinsicBlur.setInput(allocation)
                scriptIntrinsicBlur.forEach(allocation)
            }

        for (i in 1..repeat)
        {scriptIntrinsicBlur.forEach(allocation)}

        allocation.copyTo(bitmap)

        allocation.destroy()
        scriptIntrinsicBlur.destroy()
        allocation = null
        scriptIntrinsicBlur = null

       return bitmap

    }
}