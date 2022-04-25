package com.example.asteroid

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import java.util.*
import kotlin.collections.ArrayList

private const val TOP_SCREEN: Float = 60f

class SpaceShooter(context: Context) : View(context) {
    private lateinit var actionNave: ActionNav
    private var shoots = ArrayList<Shoot>()
    private var runnable: Runnable
    private lateinit var nave: Nave
    private val lifes: Int = 1000
    private var screen: Pair<Int, Int>
    private val points: Int = 0
    private var background: BackgroundScreen
    private var paused: Boolean
    private var myHandler = Handler(Looper.getMainLooper())
    private var random = Random()

    init {
        screen = getScreen(context as Activity)
        background = BackgroundScreen(context, whidth = screen.first, height = screen.second)
        println("screen = $screen")
        paused = true
        actionNave = ActionNav.AWAIT_SHOOT
        runnable = Runnable { invalidate() }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawImages(canvas)
    }

    private fun drawImages(canvas: Canvas?) {
        canvas?.apply {
            drawBackground()
            drawPoints()
            drawLife()
            drawNave()
            drawShoot()

            startGame()

            when (actionNave) {
                ActionNav.START_SHOOT -> drawShooting()
            }
        }
    }

    private fun startGame() {
        if (paused) {
            myHandler.postDelayed(runnable, 1000)
        }
    }

    private fun Canvas.drawPoints() {
        this.drawText("Points: $points", 30f, TOP_SCREEN, scorePaint)
    }

    private fun Canvas.drawLife() {
        this.drawText("Life: $lifes", (screen.first - 100).toFloat(), TOP_SCREEN, lifePaint)
    }

    private fun Canvas.drawBackground() {
        val bitmap = background.bitmap()
        this.drawBitmap(bitmap, 0f, 0f, null)
        this.drawBitmap(bitmap.rotateBitmap(60f), 0f, (screen.second / 2).toFloat(), null)
    }

    private fun Canvas.drawNave() {
        val pX = (screen.first / 2).toFloat()
        val pY = (screen.second - 200).toFloat()
        nave = Nave(context, positionX = pX, positionY = pY)
        this.drawBitmap(nave.bitmap(), pX, pY, null)
    }

    private fun Canvas.drawShoot() {
        if (::nave.isInitialized) {
            val pX = nave.positionX + 56
            var pY = nave.positionY - 100

            val shot = Shoot(context, positionX = pX, positionY = pY)
            shoots.add(shot)
            actionNave = ActionNav.START_SHOOT
        }
    }

    private fun Canvas.drawShooting() {
        shoots.forEach { shoot ->

            var intRandom = shoot.positionY.toInt()
            val nextInt = random.nextInt(intRandom)

            var positionY:Float = if (nextInt > shoot.positionY) intRandom.toFloat()
            else shoot.positionY

            positionY -= 100
            println("positionY => $positionY")

            this.drawBitmap(shoot.bitmap(), shoot.positionX, positionY, null)
            shoots[shoots.size - 1].positionY += 100
        }
    }

    private val lifePaint =
        Paint().apply {
            color = Color.RED
            textAlign = Paint.Align.LEFT
            textSize = 20f
        }

    private val scorePaint =
        Paint().apply {
            color = Color.RED
            textAlign = Paint.Align.LEFT
            textSize = 20f
        }

    private fun getScreen(activity: Activity): Pair<Int, Int> {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels
        return Pair(width, height)
    }

    private fun gameOver() {
        paused = true
        Log.e("Splash", "Game Over")
    }

    enum class ActionNav {
        START_SHOOT,
        STOP_SHOOT,
        AWAIT_SHOOT
    }
}
