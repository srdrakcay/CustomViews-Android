package com.serdar.customviews_android.basic_shape.line

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.serdar.customviews_android.Scaffold

class BasicShapesLineView : Scaffold {
    private val paint= Paint()

    //Using these variable under the onSizeChanged for the optimize the line every size device
    private var lineXLeft=0f
    private var lineXRight=0f
    private var lineYPos=0f
    private var lineHeight=0f

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
        val lineMarginHorizontal=dpToPx(LINE_MARGIN_HORIZONTAL_DP)
        lineXLeft=lineMarginHorizontal
        lineXRight=w-lineMarginHorizontal
        lineYPos=h* LINE_Y_POS_FRACTION
        lineHeight=dpToPx(LINE_HEIGHT_DP)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // Working only one time when the fragment calling

        paint.color=Color.RED
        paint.style=Paint.Style.STROKE
        paint.strokeWidth=5f
        canvas?.drawLine(lineXLeft,lineYPos,lineXRight,lineYPos,paint)
    }
    companion object {
        const val LINE_Y_POS_FRACTION = 0.1f
        const val LINE_MARGIN_HORIZONTAL_DP = 20f
        const val LINE_HEIGHT_DP = 5f
    }
}