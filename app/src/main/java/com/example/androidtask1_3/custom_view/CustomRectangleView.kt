package com.example.androidtask1_3.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class CustomRectangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private var fillPercentage = 0

    init {
        paint.color = getRandomColor()
    }

    private fun getRandomColor(): Int {
        return Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()


        val fillHeight = height * (fillPercentage / 100f)
        canvas.drawRect(0f, height - fillHeight, width, height, paint)

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        canvas.drawRect(0f, 0f, width, height, paint)

        paint.style = Paint.Style.FILL
        paint.color = getRandomColor()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            fillPercentage += 10
            if (fillPercentage > 100) {
                fillPercentage = 0
            }
            invalidate()
            return true
        }
        return super.onTouchEvent(event)
    }
}
