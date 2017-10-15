package com.example.charlie.assignment02

import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val thumbnailGrid = this.findViewById(R.id.Thumbnails) as GridView
        val thumbnailAdapter = thumbnail_adapter(this, R.layout.thumbnail_layout, thumbnail_data)
        thumbnailGrid.adapter = thumbnailAdapter

        newButton.setOnClickListener {
            val intent = Intent(applicationContext, DrawActivity::class.java)
            startActivity(intent)
        }

    }

    val thumbnail_data : ArrayList<thumbnail_layout>
    get() {
        val thumbnails : ArrayList<thumbnail_layout> = ArrayList<thumbnail_layout>()
        // TODO: Load thumbnails from disk
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher))
        return thumbnails
    }
}
