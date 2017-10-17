package com.example.charlie.assignment02

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.provider.Settings
import android.util.AttributeSet
import android.view.View

/**
 * Created by Charlie on 10/16/2017.
 */
class BrushSettings : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    val sliderRect: RectF = RectF()

    // Painting globals
    var lineColorRed: String = "cc"
    var lineColorGreen: String = "cc"
    var lineColorBlue: String = "cc"
    val sliderPaint: Paint = {
        val sliderPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        sliderPaint.color = Color.DKGRAY
        sliderPaint
    }()
    var linePaint: Paint = {
        var linePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        linePaint.color = Color.LTGRAY
        linePaint.strokeWidth = 25.0F
        linePaint
    }()

    // Used to set the line color from the activity, if color is invalid just be gray
    fun setLineColor(red: String, blue: String, green: String) {
        lineColorRed = red.toLowerCase()
        lineColorGreen = green.toLowerCase()
        lineColorBlue = blue.toLowerCase()
        try {
            linePaint.color = Color.parseColor("#$lineColorRed$lineColorGreen$lineColorBlue")
            GlobalBrushSettings.Brush.color = linePaint.color
            GlobalBrushSettings.color = linePaint.color
        }
        catch(e: Exception) {
            linePaint.color = Color.LTGRAY
            GlobalBrushSettings.Brush.color = Color.LTGRAY
            GlobalBrushSettings.color = Color.LTGRAY
        }
        invalidate()
    }

    // Used to set the line join type, which is honestly invisible to me but I logged
    // It out and this definitely works
    fun setJoinType(type: String) {
        if (type == "round") {
            linePaint.strokeJoin = Paint.Join.ROUND
            GlobalBrushSettings.Brush.strokeJoin = Paint.Join.ROUND
            GlobalBrushSettings.join = "round"
        }
        else if (type == "bevel") {
            linePaint.strokeJoin = Paint.Join.BEVEL
            GlobalBrushSettings.Brush.strokeJoin = Paint.Join.BEVEL
            GlobalBrushSettings.join = "bevel"
        }
        else if (type == "miter") {
            linePaint.strokeJoin = Paint.Join.MITER
            GlobalBrushSettings.Brush.strokeJoin = Paint.Join.MITER
            GlobalBrushSettings.join = "miter"
        }
        else {
            return
        }
        invalidate()
    }

    // Used to set the line cap type
    fun setCapType(type: String) {
        if (type == "round") {
            linePaint.strokeCap = Paint.Cap.ROUND
            GlobalBrushSettings.Brush.strokeCap = Paint.Cap.ROUND
            GlobalBrushSettings.cap = "round"
        }
        else if (type == "butt") {
            linePaint.strokeCap = Paint.Cap.BUTT
            GlobalBrushSettings.Brush.strokeCap = Paint.Cap.BUTT
            GlobalBrushSettings.cap = "butt"
        }
        else if (type == "square") {
            linePaint.strokeCap = Paint.Cap.SQUARE
            GlobalBrushSettings.Brush.strokeCap = Paint.Cap.SQUARE
            GlobalBrushSettings.cap = "square"
        }
        else {
            return
        }
        invalidate()
    }

    // Sets the line size
    fun setLineSize(size: Float) {
        linePaint.strokeWidth = size
        GlobalBrushSettings.Brush.strokeWidth = size
        GlobalBrushSettings.sWidth = size.toString()
        invalidate()
    }

    // Draw the view
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Smart cast canvas for rest of function
        if (canvas !is Canvas) {
            return
        }
        // Calculate where to draw
        val availableWidth: Float = (width - paddingLeft - paddingRight).toFloat()
        val availableHeight: Float = (height - paddingTop - paddingBottom).toFloat()

        // Set the dimensions of the view
        sliderRect.left = paddingLeft.toFloat()
        sliderRect.right = availableWidth
        sliderRect.top = paddingTop.toFloat()
        sliderRect.bottom = availableHeight

        // Offset is for drawing the demo line
        val rightOffset = sliderRect.right*0.9F
        val leftOffset = sliderRect.right - rightOffset
        // Draw it all
        canvas.drawRect(sliderRect, sliderPaint)
        canvas.drawLine(leftOffset, 50.0F, sliderRect.centerX(), sliderRect.centerY()*1.8F, linePaint)
        canvas.drawLine(sliderRect.centerX(), sliderRect.centerY()*1.8F, rightOffset, 50.0F, linePaint)
        canvas.drawLine(leftOffset, 50.0F, sliderRect.centerX(), sliderRect.centerY(), linePaint)
    }
}