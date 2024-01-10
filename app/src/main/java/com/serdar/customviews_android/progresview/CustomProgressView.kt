package com.serdar.customviews_android.progresview



import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ProgressBar
import com.serdar.customviews_android.R
import com.serdar.customviews_android.touchwithmove.TouchPositionListener

class CustomProgressView(context: Context, attrs: AttributeSet) : ProgressBar(context, attrs){

    private val progressPaint = Paint()

    init {
        progressPaint.color = Color.RED
    }
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
            }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

    }


}