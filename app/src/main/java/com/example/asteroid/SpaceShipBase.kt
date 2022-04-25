package com.example.asteroid

import android.graphics.Bitmap

abstract class SpaceShipBase(
    private val bitmap: Bitmap,
    private val whidth: Int,
    private val heigth: Int
) {
    fun bitmap() = bitmap.resize(whidth, heigth)

    fun shipWhitdh() = bitmap.width
    fun shipHeigth() = bitmap.height

    companion object {
        const val SIZE_ICON = 120
        const val SIZE_SHOT = 30
    }
}
