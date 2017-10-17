package com.example.charlie.assignment02

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Charlie on 10/15/2017.
 */
class FingerStrokeJson {
    var lineTo : String = ""
    var moveTo: String = ""
    var color: String = ""
    var strokeCap : String = ""
    var strokeJoin : String = ""
    var strokeWidth : String = ""
    var underLyingJSON : JSONObject = JSONObject()

    fun addCoordLineTo(x : Float, y : Float) {
        lineTo = lineTo + "$x,$y,"
    }
    fun addCoordMoveTo(x : Float, y : Float) {
        moveTo = moveTo + "$x,$y,"
    }
    fun addBrush(c: Int, sJoin : String, sCap : String, sWidth : String) {
        color = color + "$c,"
        strokeJoin = strokeJoin + "$sJoin,"
        strokeCap = strokeCap + "$sCap,"
        strokeWidth = sWidth + "$sWidth"
    }

    fun toJSON() : String {
        underLyingJSON.put("lineTo", lineTo)
        underLyingJSON.put("moveTo", moveTo)
        underLyingJSON.put("color", color)
        underLyingJSON.put("strokeJoin", strokeJoin)
        underLyingJSON.put("strokeCap", strokeCap)
        underLyingJSON.put("strokeWidth", strokeWidth)
        return underLyingJSON.toString()
    }
}