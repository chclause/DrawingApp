package com.example.charlie.assignment02

import android.content.Intent
import android.graphics.Path
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_draw.*
import java.io.FileNotFoundException
import java.util.Collections.copy
import javax.microedition.khronos.opengles.GL

class DrawActivity : AppCompatActivity() {

    var strokes : HashSet<FingerStroke> = HashSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            strokes = GlobalDataset.getASet(savedInstanceState.getInt("canvasIndex"))
        }
        setContentView(R.layout.activity_draw)
        current_canvas.drawAllStrokes(strokes)
        current_canvas.refreshDrawableState()
        undoButton.setOnClickListener {
            current_canvas.undoLastTouch()
        }
        redoButton.setOnClickListener {
            current_canvas.redoLastTouch()
        }
        settingsButton.setOnClickListener {
            val intent = Intent(applicationContext, BrushSettingsActivity::class.java)
            startActivity(intent)
        }
        if (GlobalBrushSettings.firstUse) {
            GlobalBrushSettings.setDefaults()
        }
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putInt("canvasIndex", GlobalDataset.addASet(HashSet<FingerStroke>(current_canvas.allStrokes)))
        super.onSaveInstanceState(outState)
    }


}
