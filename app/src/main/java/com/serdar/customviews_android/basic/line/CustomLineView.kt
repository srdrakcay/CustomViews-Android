package com.serdar.customviews_android.basic.line

import com.serdar.customviews_android.R


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class CustomLineView : View {
    private val paintMember= Paint()
    private val paintBronze= Paint()
    private val paintSilver= Paint()
    private val paintGold= Paint()

    private var lineXLeft=0f
    private var lineXRight=0f
    private var lineYPos=0f
    private var lineHeight=0f
    private var lineWith=0f

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
        lineXRight=w/4f
        lineYPos=h* LINE_Y_POS_FRACTION
        lineHeight=dpToPx(LINE_HEIGHT_DP)
        lineWith=w.toFloat()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Working only one time when the fragment calling
        // Member
        paintMember.color= resources.getColor(R.color.black)
        paintMember.style= Paint.Style.STROKE
        paintMember.strokeWidth=50f
        val startColor = Color.RED
        val endColor = Color.BLUE

        val gradientMember = LinearGradient(
            lineXLeft,
            lineYPos,
            lineXRight,
            lineYPos,
            startColor,
            endColor,
            Shader.TileMode.CLAMP
        )
        paintMember.shader = gradientMember
        canvas?.drawLine(lineXLeft,lineYPos,lineXRight,lineYPos,paintMember)
        val radius = 0.3f
        val rectF = RectF(
            lineXLeft,
            lineYPos - radius,
            lineXLeft + 2 * radius,
            lineYPos + radius
        )
        //StartArc
        canvas?.drawArc(rectF, 90f, 180f, false, paintMember)

        //Bronze
        val bronzeLineXLeft = lineXRight + 10f
        val bronzeLineXRight = lineXRight+lineWith/4
        paintBronze.color= resources.getColor(R.color.black)
        paintBronze.style= Paint.Style.STROKE
        paintBronze.strokeWidth=50f
        val gradientBronze = LinearGradient(
            bronzeLineXLeft,
            lineYPos,
            bronzeLineXRight,
            lineYPos,
            startColor,
            endColor,
            Shader.TileMode.CLAMP
        )
        paintBronze.shader = gradientBronze
        canvas?.drawLine(bronzeLineXLeft, lineYPos, bronzeLineXRight, lineYPos, paintBronze)

        //Silver
        val silverLineXLeft = bronzeLineXRight + 10f
        val silverLineXRight = silverLineXLeft + lineWith / 4
        paintSilver.color= resources.getColor(R.color.black)
        paintSilver.style= Paint.Style.STROKE
        paintSilver.strokeWidth=50f
        val gradientSilver = LinearGradient(
            silverLineXLeft,
            lineYPos,
            silverLineXRight,
            lineYPos,
            startColor,
            endColor,
            Shader.TileMode.CLAMP
        )
        paintSilver.shader = gradientSilver
        canvas?.drawLine(silverLineXLeft, lineYPos, silverLineXRight, lineYPos, paintSilver)

        //Gold
        val goldLineXLeft = silverLineXRight + 10f
        val goldLineXRight = goldLineXLeft + lineWith / 4
        paintGold.color= resources.getColor(R.color.black)
        paintGold.style= Paint.Style.STROKE
        paintGold.strokeWidth=50f
        val gradientGold = LinearGradient(
            goldLineXLeft,
            lineYPos,
            goldLineXRight,
            lineYPos,
            startColor,
            endColor,
            Shader.TileMode.CLAMP
        )
        paintGold.shader = gradientGold
        canvas?.drawLine(goldLineXLeft, lineYPos, goldLineXRight-60f, lineYPos, paintGold)
        val centerX = goldLineXRight - radius-60f
        val centerY = lineYPos
        val startAngle = 90f
        val sweepAngle = 180f

        val goldRectF = RectF(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )
        //EndArc
        canvas?.drawArc(goldRectF, startAngle, sweepAngle, false, paintGold)
    }
    companion object {
        const val LINE_Y_POS_FRACTION = 0.1f
        const val LINE_MARGIN_HORIZONTAL_DP = 20f
        const val LINE_HEIGHT_DP = 5f
    }
    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics,
        )
    }
}