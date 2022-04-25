package com.example.asteroid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Shoot constructor(
    context: Context,
    bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.shot),
    val positionX: Float,
    var positionY: Float
) : SpaceShipBase(bitmap = bitmap, 10 , 60) {
    override fun toString(): String {
        return String.format("%s, %s", positionX, positionY)
    }
}