package com.serdar.customviews_android.basic_shape_line

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.serdar.customviews_android.Scaffold

class BasicShapesView : Scaffold {
    private val paint= Paint()
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
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color=Color.RED
        paint.style=Paint.Style.STROKE
        paint.strokeWidth=5f


        canvas?.drawLine(dpToPx(LINE_MARGIN_HORIZONTAL_DP),height*LINE_Y_POS_FRACTION,width.toFloat()-dpToPx(LINE_MARGIN_HORIZONTAL_DP),height*LINE_Y_POS_FRACTION,paint)
    }
    companion object {
        const val LINE_Y_POS_FRACTION = 0.1f
        const val LINE_MARGIN_HORIZONTAL_DP = 20f
        const val LINE_HEIGHT_DP = 5f
        const val RECTANGLE_MARGIN_HORIZONTAL_DP = 20f
        const val CIRCLE_Y_POS_FRACTION = 0.8f
        const val CIRCLE_RADIUS_FRACTION = 0.1f
    }
}