package com.serdar.customviews_android.touchwithmove



import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.serdar.customviews_android.R

@SuppressLint("ResourceAsColor")
class CustomTouchView(context: Context, attrs: AttributeSet) : View(context, attrs),TouchPositionListener {
    private var circleX = 0f
    private var circleY = 0f
    private var bitmapLeft = 0f
    private var bitmapRight = 0f
    private val radius = 200f
    private val circlePaint = Paint()
    private var mBitmap: Bitmap? = null


    init {
        circlePaint.color = Color.BLUE

        val gradientGold = LinearGradient(
            circleX - radius, circleY - radius, // Başlangıç noktası
            circleX + radius, circleY + radius, // Bitiş noktası
           R.color.black, R.color.white,
            Shader.TileMode.CLAMP
        )
        circlePaint.shader = gradientGold
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(circleX, circleY, radius, circlePaint)
        mBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_pc)

        mBitmap?.let {
            val scaledBitmap = Bitmap.createScaledBitmap(it,  400,  400, true)
            canvas.drawBitmap(scaledBitmap, bitmapLeft, bitmapRight, Paint())
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        circleY= (h/2).toFloat()
        circleX= (w/2).toFloat()
        bitmapLeft= (w/2).toFloat()-2*radius
        bitmapRight= (h/2).toFloat()-2*radius
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                return true
            }
            MotionEvent.ACTION_MOVE -> {
                positionListener(event.x,event.y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP->{
               startAnimation()
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }
    override fun positionListener(x: Float, y: Float) {
        super.positionListener(x, y)
        circleX=x
        circleY=y
        bitmapLeft=circleY-2*radius
        bitmapRight=circleX-2*radius
    }
    private fun startAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            circleX = (width/2).toFloat() * animatedValue
            circleY = (height/2).toFloat() * animatedValue
            bitmapLeft = (width/2).toFloat()-2*radius * animatedValue
            bitmapRight = (height/2).toFloat()-2*radius * animatedValue
            invalidate()
        }
        animator.duration = 1000
        animator.start()
    }
}