package com.serdar.customviews_android.learning.touch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.Random

class CustomTouchEvent(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var circleX = 0f
    private var circleY = 0f
    private val radius = 100f
    private val circlePaint = Paint()

    init {
        circlePaint.color = Color.BLUE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(circleX, circleY, radius, circlePaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        circleY= (h/2).toFloat()
        circleX= (w/2).toFloat()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                circleX = event.x
                circleY = event.y
                circlePaint.color= generateRandomColor()
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                circleX = event.x
                circleY = event.y
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }
    private fun generateRandomColor(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}