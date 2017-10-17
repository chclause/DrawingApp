package com.example.charlie.assignment02

import android.content.Context
import android.content.res.Configuration
import android.graphics.*
import android.provider.Settings
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.util.*
import kotlin.collections.HashSet

/*
    This class represents the view that a user can draw on
 */
class drawing_canvas : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    // Collections of FingerStroke objects, which hold a Path and Paint object
    var undoStack : Stack<FingerStroke> = Stack<FingerStroke>()
    var redoStack : Stack<FingerStroke> = Stack<FingerStroke>()
    var allStrokes : HashSet<FingerStroke> = HashSet<FingerStroke>()
    var selfJSON : FingerStrokeJson = FingerStrokeJson()

    // The current Path that needs to be drawn
    var fingerPath : Path = Path()

    // The canvas to draw
    var drawCanvas : Canvas = Canvas()

    // To help with sizing and drawing
    lateinit var canvasBitmap : Bitmap

    // The Paint for the background
    var backgroundPaint: Paint = {
        var backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        backgroundPaint.color = Color.WHITE
        backgroundPaint
    }()

    // Sizing
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(canvasBitmap)
    }

    // Draws the canvas
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawBitmap(canvasBitmap, 0.toFloat(), 0.toFloat(), backgroundPaint)
        drawAllStrokes(allStrokes)
    }

    // User touch event.  Sets the current Path to draw and adds it to the undoStack
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null)
            return false

        var X : Float = event.getX()
        var Y : Float = event.getY()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                selfJSON.addCoordMoveTo(X, Y)
                fingerPath.moveTo(X, Y)
            }
            MotionEvent.ACTION_MOVE -> {
                selfJSON.addCoordLineTo(X, Y)
                fingerPath.lineTo(X, Y)
            }
            MotionEvent.ACTION_UP -> {
                selfJSON.addCoordLineTo(X, Y)
                fingerPath.lineTo(X, Y)
                val fingerStroke : FingerStroke = FingerStroke(fingerPath, GlobalBrushSettings.Brush)
                drawCanvas.drawPath(fingerPath, GlobalBrushSettings.Brush)
                undoStack.push(fingerStroke)
                allStrokes.add(fingerStroke)
                fingerPath.reset()
            }
            else -> return false
        }
        selfJSON.addBrush(GlobalBrushSettings.color,
                GlobalBrushSettings.join,
                GlobalBrushSettings.cap,
                GlobalBrushSettings.sWidth
                )
        invalidate()
        return true
    }

    fun drawAllStrokes(strokes : HashSet<FingerStroke>) {
        if (GlobalDataset.globalPathData.contains(allStrokes))
            GlobalDataset.globalPathData.remove(allStrokes)
        allStrokes = strokes
        for (item in strokes) {
            drawCanvas.drawPath(item.fingerPath, item.fingerPaint)
        }
        invalidate()
    }

    // Function used by the undoButton to pop the undo stack and erase that stroke
    fun undoLastTouch() {
        if (undoStack.empty())
            return
        val undo : FingerStroke = undoStack.pop()
        redoStack.push(undo)
        allStrokes.remove(undo)
        val c : Int = undo.fingerPaint.color
        undo.fingerPaint.color = backgroundPaint.color
        drawCanvas.drawPath(undo.fingerPath, undo.fingerPaint)
        undo.fingerPaint.color = c
        invalidate()
    }
    // Function used by the redoButton to pop the redo stack and draw that stroke
    fun redoLastTouch() {
        if (redoStack.empty())
            return
        val redo : FingerStroke = redoStack.pop()
        undoStack.push(redo)
        allStrokes.add(redo)
        drawCanvas.drawPath(redo.fingerPath, redo.fingerPaint)
        invalidate()
    }
}