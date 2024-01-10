package com.serdar.customviews_android.learning.animated_float_action

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import com.serdar.customviews_android.databinding.LayoutCustomFloatActionBinding

class CustomFloatActionView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet, defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private val _binding =
        LayoutCustomFloatActionBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(_binding.root)
        openFloatButton()
    }

    private val translateDeleteViewAnimation: ObjectAnimator = ObjectAnimator.ofFloat(
        _binding.ivPerson, View.TRANSLATION_Y, 0f, 200f
    )
    private val translatePersonViewAnimation: ObjectAnimator = ObjectAnimator.ofFloat(
        _binding.ivPerson, View.TRANSLATION_Y, 0f, 200f
    )
    private val translateLockViewAnimation: ObjectAnimator = ObjectAnimator.ofFloat(
        _binding.ivLock, View.TRANSLATION_Y, 0f, 200f
    )

    private fun openFloatButton() {
        _binding.root.setOnClickListener {
            setViewVisibility(SHOW_ALL_VIEWS)
            setDeleteViewAnimations()
            setPersonViewAnimations()
            setLockViewAnimations()
        }
    }

    private fun setViewVisibility(type: Int) {
        when (type) {
            0 -> {
                _binding.ivDelete.show()
                _binding.ivPerson.show()
                _binding.ivLock.show()
            }

            1 -> {
                _binding.ivDelete.notShow()
                _binding.ivPerson.notShow()
                _binding.ivLock.notShow()
            }
        }
    }

    private fun setDeleteViewAnimations() {
        val path = Path().apply {
            arcTo(0f, 0f, height.toFloat()/2, width.toFloat()/2, 0f, -270f, true)
            val animator =
                ObjectAnimator.ofFloat(_binding.ivDelete, View.X, View.Y, this).apply {
                    duration = 2000
                    start()
                }
        }

    }

    private fun setPersonViewAnimations() {
        translatePersonViewAnimation.duration = 2000
        translatePersonViewAnimation.interpolator = AccelerateDecelerateInterpolator()
        translatePersonViewAnimation.start()
        translatePersonViewAnimationListener
    }

    private fun setLockViewAnimations() {
        translateLockViewAnimation.duration = 2000
        translateLockViewAnimation.interpolator = AccelerateDecelerateInterpolator()
        translateLockViewAnimation.start()
        translateLockViewAnimationListener
    }

    private val translateDeleteViewAnimationListener =
        translateDeleteViewAnimation.addListener(object : AnimationContractListener() {
            override fun onAnimationStart(p0: Animator) {
                setLayerType(LAYER_TYPE_HARDWARE, null)
            }

            override fun onAnimationEnd(p0: Animator) {
                setLayerType(LAYER_TYPE_NONE, null)

            }
        })
    private val translatePersonViewAnimationListener =
        translatePersonViewAnimation.addListener(object : AnimationContractListener() {
            override fun onAnimationStart(p0: Animator) {
                setLayerType(LAYER_TYPE_HARDWARE, null)
            }

            override fun onAnimationEnd(p0: Animator) {
                setLayerType(LAYER_TYPE_NONE, null)
            }
        })
    private val translateLockViewAnimationListener =
        translateLockViewAnimation.addListener(object : AnimationContractListener() {
            override fun onAnimationStart(p0: Animator) {
                setLayerType(LAYER_TYPE_HARDWARE, null)
            }

            override fun onAnimationEnd(p0: Animator) {
                setLayerType(LAYER_TYPE_NONE, null)
            }
        })

    companion object {
        const val SHOW_ALL_VIEWS = 0
        const val NOT_SHOW_ALL_VIEWS = 1
    }
}
