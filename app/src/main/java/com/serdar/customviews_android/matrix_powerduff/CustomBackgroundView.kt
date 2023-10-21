package com.serdar.customviews_android.matrix_powerduff

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.serdar.customviews_android.R
import java.lang.Float.min

class CustomBackgroundView : View {
    private val actualBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.un_filtered)

    private val segmentedBitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_segmented)
    private val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    private val imageRectF = RectF()
    private val imageMatrix = Matrix()

    private val imagePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val backgroundBitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_background)
    private val backgroundMatrix = Matrix()
    private val backgroundBitmapRectF = RectF()
    private val imageClipRectF = RectF()

    private val viewRectF = RectF()
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
        viewRectF.set(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())
         updateImageMatrix()
         updateBackgroundImageMatrix()

    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.clipRect(imageClipRectF)
        canvas?.drawBitmap(backgroundBitmap,backgroundMatrix,imagePaint)
        canvas?.saveLayer(viewRectF, imagePaint)
        canvas?.drawBitmap(actualBitmap, imageMatrix, imagePaint)
        canvas?.drawBitmap(segmentedBitmap, imageMatrix, maskPaint)
        canvas?.restore()


    }
    private fun updateImageMatrix() {
        actualBitmap.runIfSafe {
            imageRectF.set(0f, 0f, it.width.toFloat(), it.height.toFloat())

            val widthScale = viewRectF.width() / imageRectF.width()
            val heightScale = viewRectF.height() / imageRectF.height()

            val scaleFactor = min(widthScale, heightScale)

            val translateX = (viewRectF.width() - imageRectF.width() * scaleFactor) / 2f
            val translateY = (viewRectF.height() - imageRectF.height() * scaleFactor) / 2f

            imageMatrix.setScale(scaleFactor, scaleFactor)
            imageMatrix.postTranslate(translateX, translateY)

            invalidate()
        }
    }

    private fun updateBackgroundImageMatrix() {
        backgroundBitmap.runIfSafe {
            backgroundBitmapRectF.set(0f, 0f, it.width.toFloat(), it.height.toFloat())

            val widthScale = viewRectF.width() / backgroundBitmapRectF.width()
            val heightScale = viewRectF.height() / backgroundBitmapRectF.height()

            val scaleFactor = min(widthScale, heightScale)

            val translateX = (viewRectF.width() - backgroundBitmapRectF.width() * scaleFactor) / 2f
            val translateY =
                (viewRectF.height() - backgroundBitmapRectF.height() * scaleFactor) / 2f

            backgroundMatrix.setScale(scaleFactor, scaleFactor)
            backgroundMatrix.postTranslate(translateX, translateY)

            backgroundMatrix.mapRect(imageClipRectF, backgroundBitmapRectF)

            invalidate()
        }
    }

}

private fun Bitmap?.runIfSafe(function: (Bitmap) -> Unit) {
    this ?: return

    if (isRecycled.not()) {
        function(this)
    }
}