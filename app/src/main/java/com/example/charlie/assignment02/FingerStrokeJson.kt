package com.example.charlie.assignment02

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Charlie on 10/15/2017.
 */
class FingerStrokeJson {
    var downPathJSON : JSONArray = JSONArray()
    var movePathJSON : JSONArray = JSONArray()
    var upPathJSON : JSONArray = JSONArray()
    var underLyingJSON : JSONObject = JSONObject()

    fun addCoordDownPath(x : Float, y: Float) {
        val newCoord : JSONObject = JSONObject()
        newCoord.put("x", x.toString())
        newCoord.put("y", y.toString())
        downPathJSON.put(newCoord)
    }
    fun addCoordUpPath(x : Float, y : Float) {
        val newCoord : JSONObject = JSONObject()
        newCoord.put("x", x.toString())
        newCoord.put("y", y.toString())
        upPathJSON.put(newCoord)
    }
    fun addCoordMovePath(x : Float, y : Float) {
        val newCoord : JSONObject = JSONObject()
        newCoord.put("x", x.toString())
        newCoord.put("y", y.toString())
        movePathJSON.put(newCoord)
    }
    fun toJSON() : String {
        underLyingJSON.put("moveDownPath", downPathJSON)
        underLyingJSON.put("moveUpPath", upPathJSON)
        underLyingJSON.put("movePath", movePathJSON)

        return underLyingJSON.toString()
    }

}