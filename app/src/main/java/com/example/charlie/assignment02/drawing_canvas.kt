package com.example.charlie.assignment02

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.util.*

/**
 * Created by Charlie on 10/14/2017.
 */
class drawing_canvas : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    public var undoStack : Stack<FingerStroke> = Stack<FingerStroke>()
    public var redoStack : Stack<FingerStroke> = Stack<FingerStroke>()

    var fingerPath : Path = Path()

    var drawCanvas : Canvas = Canvas()

    lateinit var canvasBitmap : Bitmap


    var lineColorRed : String = "cc"
    var lineColorGreen : String = "cc"
    var lineColorBlue : String = "cc"


    var linePaint: Paint = {
        var linePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        linePaint.color = Color.LTGRAY
        linePaint.strokeWidth = 25.0F
        linePaint
    }()
    var backgroundPaint: Paint = {
        var backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        backgroundPaint.color = Color.WHITE
        backgroundPaint
    }()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(canvasBitmap)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawBitmap(canvasBitmap, 0.toFloat(), 0.toFloat(), backgroundPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null)
            return false

        var X : Float = event.getX()
        var Y : Float = event.getY()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> fingerPath.moveTo(X, Y)
            MotionEvent.ACTION_MOVE -> fingerPath.lineTo(X, Y)
            MotionEvent.ACTION_UP -> {
                fingerPath.lineTo(X, Y)
                val fingerStroke : FingerStroke = FingerStroke(fingerPath, linePaint)
                drawCanvas.drawPath(fingerPath, linePaint)
                undoStack.push(fingerStroke)
                fingerPath.reset()
            }
            else -> return false
        }
        invalidate()
        return true
    }

    fun undoLastTouch() {
        if (undoStack.empty())
            return
        Log.e("TAG", "UNDO")
        val undo : FingerStroke = undoStack.pop()
        redoStack.push(undo)
        val c : Int = undo.fingerPaint.color
        undo.fingerPaint.color = Color.WHITE
        drawCanvas.drawPath(undo.fingerPath, undo.fingerPaint)
        undo.fingerPaint.color = c
        invalidate()
    }
    fun redoLastTouch() {
        if (redoStack.empty())
            return
        val redo : FingerStroke = redoStack.pop()
        undoStack.push(redo)
        drawCanvas.drawPath(redo.fingerPath, redo.fingerPaint)
        invalidate()
    }
}