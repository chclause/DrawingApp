package com.example.charlie.assignment02

import android.graphics.Paint
import android.graphics.Path
import android.os.Environment
import android.util.Log
import org.json.JSONObject
import java.io.File
import java.io.OutputStream
import java.io.OutputStreamWriter

object GlobalDataset {
    private const val datasetDirectoryName : String = "FingerStrokeCollection"
    private const val fingerStrokeExtension : String = ".fingerstrokes"

    private val canReadExternalStorage : Boolean
        get() = when (Environment.getExternalStorageState()) {
            Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY -> true
            else -> false
        }
    private val canWriteToExternalStorage : Boolean
        get() = Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()

    var globalPathData = arrayListOf<HashSet<FingerStroke>>()
    var globalJsonPaths = arrayListOf<FingerStrokeJson>()

    fun addASet(strokes : HashSet<FingerStroke>) : Int {
        if (!globalPathData.contains((strokes)))
            globalPathData.add(strokes)
        saveDataset()
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

    private fun saveDataset() {
        if (canWriteToExternalStorage) {
            val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), datasetDirectoryName)
            directory.deleteRecursively()

            if (!directory.exists()) {
                check(directory.mkdirs()) { "External storage was marked as readable, but a directory could not be created there."}
            }
            var index: Int = 0
            for (item in globalJsonPaths) {
                var file = File(directory.absolutePath + File.separator + index++ + fingerStrokeExtension)
                file.writeText(item.toJSON())
            }
        }
    }
}