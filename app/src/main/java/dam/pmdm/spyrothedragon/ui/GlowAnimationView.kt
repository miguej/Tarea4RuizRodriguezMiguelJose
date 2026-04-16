package dam.pmdm.spyrothedragon.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class GlowAnimationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var radius = 0f
    private var glowColor = Color.YELLOW
    private var centerX = 0f
    private var centerY = 0f
    
    private val animator = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = 2000
        repeatCount = ValueAnimator.INFINITE
        repeatMode = ValueAnimator.REVERSE
        interpolator = LinearInterpolator()
        addUpdateListener {
            val fraction = it.animatedValue as Float
            radius = 50f + fraction * 150f
            // Change color intensity/hue
            glowColor = Color.argb(
                (100 + fraction * 155).toInt(),
                255,
                (200 + fraction * 55).toInt(),
                (100 - fraction * 100).toInt()
            )
            invalidate()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Adjust these coordinates to match Ripto's scepter diamond position
        // This is an estimation, might need adjustment depending on the image
        centerX = w * 0.52f
        centerY = h * 0.38f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        // Draw multiple layers of glow
        for (i in 5 downTo 1) {
            paint.shader = RadialGradient(
                centerX, centerY, radius * (i / 5f),
                intArrayOf(glowColor, Color.TRANSPARENT),
                null, Shader.TileMode.CLAMP
            )
            canvas.drawCircle(centerX, centerY, radius * (i / 5f), paint)
        }
    }

    fun startAnimation() {
        animator.start()
    }

    fun stopAnimation() {
        animator.cancel()
    }
}
