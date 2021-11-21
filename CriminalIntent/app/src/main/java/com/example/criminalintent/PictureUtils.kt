package com.example.criminalintent

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point

fun getScaledBitmap(path: String, activity: Activity): Bitmap{
    val size = Point()

    @Suppress("DEPRECATION")
    activity.windowManager.defaultDisplay.getSize(size)

    return getScaledBitmap(path, size.x, size.y)
}

fun getScaledBitmap(path: String, destWidth: Int, desHeight: Int): Bitmap {

    // 이미지 파일의 크기를 읽는다.
    var options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(path, options)

    val srcWidth = options.outWidth.toFloat()
    val srcHeight = options.outHeight.toFloat()

    // 크기를 얼마나 줄일지 파악한다.
    var inSampleSize = 1

    if(srcHeight > desHeight || srcWidth > destWidth){
        val heightScale = srcHeight / desHeight
        val widthScale = srcWidth / destWidth

        val sampleScale = if (heightScale > widthScale){
            heightScale
        } else{
            widthScale
        }
        inSampleSize = Math.round(sampleScale)
    }

    options = BitmapFactory.Options()
    options.inSampleSize = inSampleSize

    // 최종 Bitmap을 생성한다.
    return BitmapFactory.decodeFile(path, options)
}