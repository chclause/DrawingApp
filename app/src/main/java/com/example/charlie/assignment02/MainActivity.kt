package com.example.charlie.assignment02

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import kotlinx.android.synthetic.main.thumbnail_layout.*
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.GridView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val writePermissionCode: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), writePermissionCode)
            }
            else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), writePermissionCode)
            }
        }

        val thumbnailGrid = this.findViewById(R.id.Thumbnails) as GridView
        val thumbnailAdapter = thumbnail_adapter(this, R.layout.thumbnail_layout, thumbnail_data)
        thumbnailGrid.adapter = thumbnailAdapter

        newButton.setOnClickListener {
            val intent = Intent(applicationContext, DrawActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    val thumbnail_data : ArrayList<thumbnail_layout>
    get() {
        val thumbnails : ArrayList<thumbnail_layout> = ArrayList<thumbnail_layout>()
        // TODO: Load thumbnails from disk
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher, "title"))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher, "title"))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher, "title"))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher, "title"))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher, "title"))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher, "title"))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher, "title"))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher, "title"))
        thumbnails.add(thumbnail_layout(R.mipmap.ic_launcher, "title"))
        return thumbnails
    }
}
