package com.serdar.customviews_android.basic.circle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.serdar.customviews_android.Scaffold

class BasicShapeCircle : Scaffold {
    private val paint= Paint()

    //Using these variable under the onSizeChanged for the optimize the circle every size device
    private var circleXCenter=0f
    private var circleYCenter=0f
    private var circleRadius=0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Working only one time when the fragment calling
        circleXCenter=w/2f
        circleYCenter=h/2f
        circleRadius=h* CIRCLE_RADIUS_POS_FRACTION
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Working only one time when the fragment calling

        paint.color= Color.RED
        paint.style= Paint.Style.STROKE
        paint.strokeWidth=5f
        canvas?.drawCircle(circleXCenter,circleYCenter,circleRadius,paint)
    }
    companion object {
        const val CIRCLE_RADIUS_POS_FRACTION =0.1f
    }
}