package com.serdar.customviews_android.basic_shape.rectangle


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import com.serdar.customviews_android.Scaffold

class BasicShapesRectangle : Scaffold {

    private val paint = Paint()
    private val rectangleRect = RectF()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )
    // Working only one time when the fragment calling
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // This is a margin for view start point
        val rectMarginHorizontal = dpToPx(RECTANGLE_MARGIN_HORIZONTAL_DP)

        //This is a view width and we have two margin (rightMargin and leftMargin)
        val rectWidth = w - 2 * rectMarginHorizontal

        //This is a rect height
        val rectHeight = rectWidth / 2

        //Rect top margin h(device height) for the center of device
        val rectTop=(h-rectHeight)/2

        val rectBottom=(h+rectHeight)/2

        rectangleRect.set(
        rectMarginHorizontal,
            rectTop,
            rectMarginHorizontal+rectWidth,
            rectBottom
        )

    }
    // Working only one time when the fragment calling
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 5f
        canvas?.drawRect(rectangleRect,paint
        )
    }

    companion object {
        const val RECTANGLE_MARGIN_HORIZONTAL_DP = 20f
    }
}