package com.serdar.customviews_android.learning.selectedpoint


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class SelectedPointView : View {
    private var circleXCenter=0f
    private var circleYCenter=0f
    private var circleRadius=0f
    private var firstXCenter=0f
    private var secondXCenter=0f
    private var thirdXCenter=0f



    val paint = Paint()
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    init {
            paint.apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color= Color.RED
        }


    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        circleXCenter= (w/2).toFloat()
        circleYCenter= (h/2).toFloat()
        circleRadius=(h/4).toFloat()* CIRCLE_RADIUS_POS_FRACTION

        firstXCenter=circleXCenter/2
        secondXCenter=firstXCenter/2
        thirdXCenter=circleXCenter


    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(firstXCenter, circleYCenter, circleRadius , paint)
        canvas.drawCircle(secondXCenter, circleYCenter, circleRadius , paint)

    }
    companion object{
        const val CIRCLE_RADIUS_POS_FRACTION =0.1f

    }
}