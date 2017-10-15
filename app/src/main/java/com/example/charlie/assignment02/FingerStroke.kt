package com.example.charlie.assignment02

import android.graphics.Paint
import android.graphics.Path

/**
 * Created by Charlie on 10/15/2017.
 */
class FingerStroke(path : Path, paint : Paint) {
    val fingerPath : Path = Path(path)
    val fingerPaint : Paint = Paint(paint)
}