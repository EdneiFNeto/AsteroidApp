package com.example.asteroid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory


class Enemy constructor(
    private val context: Context,
    bitmap: Bitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.enemy
    ),
    val positionX: Float,
    val positionY: Float
) : SpaceShipBase(bitmap = bitmap, SIZE_ICON, SIZE_ICON)