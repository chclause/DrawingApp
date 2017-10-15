package com.example.charlie.assignment02

import android.graphics.Path
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_draw.*

class DrawActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)


        undoButton.setOnClickListener {
            current_canvas.undoLastTouch()
        }
        redoButton.setOnClickListener {
            current_canvas.redoLastTouch()
        }
    }
}
