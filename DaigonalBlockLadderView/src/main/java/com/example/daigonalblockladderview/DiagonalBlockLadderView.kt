package com.example.daigonalblockladderview

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

val blocks : Int = 5
val parts : Int = 7
val scGap : Float = 0.02f / parts
val colors : Array<Int> = arrayOf(
    "#F44336",
    "#009688",
    "#3F51B5",
    "#4CAF50",
    "#009688"
).map {
    Color.parseColor(it)
}.toTypedArray()
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawDiagonalBlockLadder(scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = (w / 2) / (blocks - 0.5f)
    val sf : Float = scale.sinify()
    for (j in 0..(blocks - 1)) {
        save()
        translate(
            w / 2 - size * j * (1 - sf.divideScale(blocks, parts)),
            h - (size * (j + 1) * sf.divideScale(j, parts))
        )
        drawRect(RectF(-size / 2, -size / 2, size / 2, size/ 2), paint)
        restore()
    }
}

fun Canvas.drawDBLNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    drawDiagonalBlockLadder(scale, w, h, paint)
}
