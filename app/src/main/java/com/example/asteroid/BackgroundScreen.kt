package com.example.asteroid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BackgroundScreen constructor(
    context: Context,
    bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.background_space),
    whidth: Int,
    height: Int,
    val positionX: Float = 0f,
    val positionY: Float = 0f
) : SpaceShipBase(bitmap, whidth, height)
