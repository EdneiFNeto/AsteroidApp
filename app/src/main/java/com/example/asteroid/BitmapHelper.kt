package com.example.asteroid

import android.graphics.Bitmap
import android.graphics.Matrix

fun Bitmap.resize(width: Int, height: Int) : Bitmap{
    return Bitmap.createScaledBitmap(this, width, height, false)
}

fun Bitmap.rotateBitmap(grau: Float) : Bitmap{
    val matrix = Matrix()
    matrix.postRotate(grau)
    val scaledBitmap = Bitmap.createScaledBitmap(this, width, height, true)

    return Bitmap.createBitmap(
        scaledBitmap,
        0,
        0,
        scaledBitmap.width,
        scaledBitmap.height,
        matrix,
        true
    )
}
