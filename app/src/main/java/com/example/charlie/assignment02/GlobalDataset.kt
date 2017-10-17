package com.example.charlie.assignment02

import android.util.Log

/**
 * Created by Charlie on 10/16/2017.
 */
object GlobalDataset {
    var globalPathData = arrayListOf<HashSet<FingerStroke>>()
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

}