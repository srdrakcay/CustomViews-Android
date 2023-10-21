    package com.serdar.customviews_android

    import android.animation.Animator
    import android.animation.ValueAnimator
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
    import android.graphics.Matrix
    import android.graphics.Paint
    import android.graphics.Path
    import android.graphics.RectF
    import android.graphics.RuntimeShader
    import android.graphics.Shader
    import android.graphics.drawable.BitmapDrawable
    import android.graphics.drawable.Drawable
    import android.os.Build
    import android.util.AttributeSet
    import android.util.TypedValue
    import android.view.View
    import android.view.ViewPropertyAnimator
    import android.view.animation.DecelerateInterpolator
    import androidx.annotation.RequiresApi
    import com.serdar.customviews_android.basic.circle.BasicShapeCircle

    class CustomCircleStrokeImage : View {
        private var circleXCenter=0f
        private var circleYCenter=0f
        private var circleRadius=0f
        private var mBitmap: Bitmap? = null
        private val path = Path()
        private val rectF = RectF()
        private var mRoundOfNumber = 4
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
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        @SuppressLint("DrawAllocation")
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val paint = Paint().apply {
                isAntiAlias = true
                style = Paint.Style.STROKE
                strokeWidth = 30f
                color=Color.BLACK
                isFilterBitmap = true
                isDither = true

                shader = LinearGradient(
                    0f, 0f, 0f, height.toFloat(),
                    Color.RED, Color.BLUE,
                    Shader.TileMode.CLAMP
                )
            }


            mBitmap = BitmapFactory.decodeResource(resources, R.drawable.un_filtered)
            val bitmap = mBitmap
            bitmap?.let {

                //Circle Radius
                val radius = (width.coerceAtMost(height) / 2 - paint.strokeWidth / 2).toFloat()
                //Setting the dimensions
                rectF.set(0f, 0f, width.toFloat(), height.toFloat())
                rotateTo()
                path.addCircle(
                    rectF.centerX(),
                    rectF.centerY(),
                    (rectF.width() / 2).coerceAtMost(rectF.height() / 2),
                    Path.Direction.CCW
                )

                //Path for bitmap

                canvas?.clipPath(path)
                //Drawing bitmap
                canvas?.drawBitmap(bitmap, null, rectF, paint)
                //Draw Circle

                canvas?.drawCircle(width / 2f, height / 2f, radius + paint.strokeWidth / 2 , paint)
            }

        }

        private fun rotateTo() {
            animate()
                .setInterpolator(DecelerateInterpolator())
                .setDuration(mRoundOfNumber * 250 + 1500L)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {

                    }

                    override fun onAnimationEnd(animation: Animator) {

                    }

                    override fun onAnimationCancel(animation: Animator) {

                    }
                    override fun onAnimationRepeat(animation: Animator) {

                    }
                })
                .rotation(360f)
                .start()
        }




        companion object {
            const val CIRCLE_RADIUS_POS_FRACTION =0.1f
            val COLOR_SHADER_SRC ="0.1f"
        }

    }