package com.example.mobilep2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point

fun getScaledBitmap(path: String, activity: MainActivity): Bitmap{
    val size = Point()
    return getScaledBitmap(path, size.x, size.y)
}

fun getScaledBitmap(path: String, destWidth: Int, destHeight: Int): Bitmap{
    var options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(path, options)

    val sourceWidth = options.outWidth.toFloat()
    val sourceHeight = options.outHeight.toFloat()

    var theSampleSize = 1
    if(sourceHeight > destHeight || sourceWidth > destWidth){
        val scaledHeight = sourceHeight / destHeight
        val scaledWidth = sourceWidth / destWidth

        val sampledScale = if(scaledHeight > scaledWidth){
            scaledHeight
        } else {
            scaledWidth
        }
        theSampleSize = Math.round(sampledScale)

    }
    options = BitmapFactory.Options()
    options.inSampleSize = theSampleSize
    return BitmapFactory.decodeFile(path, options)
}