package com.serdar.customviews_android

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.serdar.customviews_android.basic.circle.BasicShapeCircle

class CustomCircleStrokeImage : View {
    private var circleXCenter=0f
    private var circleYCenter=0f
    private var circleRadius=0f
    private var paint: Paint=Paint()
    private var mBitmap: Bitmap? = null
    private val path = Path()
    private val rectF = RectF()
    private var ringColor = Color.BLACK
    private var ringWidth = 5f
    private var rainbow = false
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
        circleXCenter=w/2f
        circleYCenter=h/2f
        circleRadius=h* CIRCLE_RADIUS_POS_FRACTION

    }
    private val ringPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = ringWidth
        color=ringColor
        isFilterBitmap = true
        isDither = true
    }
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mBitmap = BitmapFactory.decodeResource(resources, R.drawable.un_filtered)
        val bitmap = mBitmap
        bitmap?.let {
            val shader = BitmapShader(it, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            paint.shader = shader
            val radius = (width.coerceAtMost(height) / 2 - ringPaint.strokeWidth / 2).toFloat()
            rectF.set(0f, 0f, width.toFloat(), height.toFloat())
            path.addCircle(
                rectF.centerX(),
                rectF.centerY(),
                (rectF.width() / 2).coerceAtMost(rectF.height() / 2),
                Path.Direction.CCW
            )
            canvas?.clipPath(path)
            canvas?.drawBitmap(bitmap, null, rectF, paint)
            canvas?.drawCircle(width / 2f, height / 2f, radius + ringPaint.strokeWidth / 2, ringPaint)
        }

    }


    companion object {
        const val CIRCLE_RADIUS_POS_FRACTION =0.1f
    }

}