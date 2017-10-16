package com.example.charlie.assignment02

import kotlinx.android.synthetic.main.activity_brush_settings.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar

class BrushSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brush_settings)


        // Change colors, fix bad values
        Red.setOnClickListener {
            LineDemo.setLineColor(RedValue.text.toString(),
                    GreenValue.text.toString(), BlueValue.text.toString())
            if (RedValue.text.toString().length > 2) {
                RedValue.setText("cc")
            }

        }
        Green.setOnClickListener {
            LineDemo.setLineColor(RedValue.text.toString(),
                    GreenValue.text.toString(), BlueValue.text.toString())
            if (GreenValue.text.toString().length > 2) {
                GreenValue.setText("cc")
            }
        }
        Blue.setOnClickListener {
            LineDemo.setLineColor(RedValue.text.toString(),
                    GreenValue.text.toString(), BlueValue.text.toString())
            if (BlueValue.text.toString().length > 2) {
                BlueValue.setText("cc")
            }
        }

        // Change join types
        BevelButton.setOnClickListener {
            LineDemo.setJoinType("bevel")
        }
        MiterButton.setOnClickListener {
            LineDemo.setJoinType("miter")
        }
        RoundJoinButton.setOnClickListener {
            LineDemo.setJoinType("round")
        }

        // Set cap types
        RoundCapButton.setOnClickListener {
            LineDemo.setCapType("round")
        }
        SquareButton.setOnClickListener {
            LineDemo.setCapType("square")
        }
        ButtButton.setOnClickListener {
            LineDemo.setCapType("butt")
        }

        // Line size
        lineSizeBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var current: Float = p1.toFloat()
                var level: Float = (current/2)
                LineDemo.setLineSize(level)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

    }
}
