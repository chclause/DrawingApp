package com.example.charlie.assignment02

import android.graphics.Paint
import android.graphics.Path
import android.util.Log
import org.json.JSONObject

/**
 * Created by Charlie on 10/16/2017.
 */
object GlobalDataset {
    var globalPathData = arrayListOf<HashSet<FingerStroke>>()
    var globalJsonPaths = arrayListOf<FingerStrokeJson>()

    fun addASet(strokes : HashSet<FingerStroke>) : Int {
        if (!globalPathData.contains((strokes)))
            globalPathData.add(strokes)
        return globalPathData.indexOf(strokes)
    }
    fun indexOfSet(strokes : HashSet<FingerStroke>) : Int {
        if (globalPathData.contains(strokes))
            return globalPathData.indexOf(strokes)
        return -1
    }
    fun getASet(index : Int) : HashSet<FingerStroke> {
        return globalPathData[index]
    }

    fun fromJSON(JSON : String) : FingerStroke {
        val json = JSONObject(JSON)
        val path : Path = Path()
        val paint : Paint = Paint()
        val moves = json["moveTo"].toString().split(",")
        val lines = json["lineTo"].toString().split(",")
        var i = 0
        paint.strokeWidth = json["strokeWidth"].toString().toFloat()
        paint.strokeJoin = parseJoin(json["strokeJoin"].toString())
        paint.strokeCap = parseCap(json["strokeCap"].toString())
        paint.color = json["color"].toString().toInt()
        // Do moveTo
        while (i < moves.size-1) {
            path.moveTo(moves[i].toFloat(), moves[i+1].toFloat())
            i+=2
        }
        i=0
        // Do lineTo
        while (i < lines.size-1) {
            path.lineTo(lines[i].toFloat(), lines[i+1].toFloat())
            i+=2
        }
        val fingerStroke = FingerStroke(path, paint)
        return fingerStroke
    }

    private fun parseJoin(type: String): Paint.Join? {
        if (type == "round")
            return Paint.Join.ROUND
        else if (type == "bevel")
            return Paint.Join.BEVEL
        else
            return Paint.Join.MITER
    }

    private fun parseCap(type: String): Paint.Cap? {
        if (type == "round")
            return Paint.Cap.ROUND
        else if (type == "butt")
            return Paint.Cap.BUTT
        else
            return Paint.Cap.SQUARE
    }
}