package com.example.charlie.assignment02

import android.graphics.Color
import android.graphics.Paint

/**
 * Created by Charlie on 10/16/2017.
 */
object GlobalBrushSettings {

    var Brush: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var firstUse : Boolean = true

    fun setDefaults() {
        firstUse = false
        Brush.color = Color.DKGRAY
        Brush.strokeJoin = Paint.Join.ROUND
        Brush.strokeCap = Paint.Cap.ROUND
        Brush.style = Paint.Style.STROKE
        Brush.strokeWidth = 25.0F
    }

}