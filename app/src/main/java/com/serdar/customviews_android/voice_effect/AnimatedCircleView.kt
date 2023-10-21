package com.serdar.customviews_android.voice_effect

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class AnimatedCircleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var circleX = 0f
    private var circleY = 0f
    private var animatedLevel = 0f
    val paint = Paint()
    private var animator: ValueAnimator= ValueAnimator()

    init {
        animator=ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener { animation ->
            animatedLevel = animation.animatedValue as Float
            invalidate()
        }
        animator.duration = 1000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.start()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        circleY= (height / 2).toFloat()
        circleX= animatedLevel * width

        paint.color=Color.BLACK
        canvas.drawCircle(circleX, circleY, 100f, paint)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                animator.start()
                circleX = event.x
                circleY = event.y
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP->{

                return true
            }
        }
        return super.onTouchEvent(event)
    }
}
