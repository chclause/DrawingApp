package com.example.charlie.assignment02

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView


class thumbnail_adapter(private val getContext : Context, private val layoutId : Int, private val thumbnailLayout : ArrayList<thumbnail_layout>)
    : ArrayAdapter<thumbnail_layout>(getContext, layoutId, thumbnailLayout) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var row = convertView
        val viewHolder : ViewHolder
        if (row == null) {
            viewHolder = ViewHolder()
            val inflater = (getContext as Activity).layoutInflater

            row = inflater!!.inflate(layoutId, parent, false)
            viewHolder.thumbnail = row!!.findViewById(R.id.thumbnail_image)

            row.setTag(viewHolder)
        } else {
            viewHolder = row.getTag() as ViewHolder
        }

        val tn = thumbnailLayout[position]
        viewHolder.thumbnail!!.setImageResource(tn.image)

        return row
    }


    class ViewHolder {
        var thumbnail : ImageView? = null
    }

}