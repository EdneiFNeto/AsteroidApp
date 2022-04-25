package com.example.asteroid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Explosion constructor(private val context: Context) {

    private val bitmap = ArrayList<Bitmap>()
    var explionFrame: Int = 0

    fun initExplostion(drawable: List<Int>, ex: Int, ey: Int) {
        drawable.forEach {
            bitmap.add(BitmapFactory.decodeResource(context.resources, it))
        }
        explionFrame = 0
    }

    fun getExplosion(index: Int) = bitmap[index]
}