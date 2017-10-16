package com.example.charlie.assignment02

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class thumbnail_adapter(private val getContext : Context, private val layoutId : Int, private val thumbnailLayouts : ArrayList<thumbnail_layout>)
    : ArrayAdapter<thumbnail_layout>(getContext, layoutId, thumbnailLayouts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var row = convertView
        val viewHolder : ViewHolder
        if (row == null) {
            viewHolder = ViewHolder()
            val inflater = (getContext as Activity).layoutInflater

            row = inflater!!.inflate(layoutId, parent, false)
            viewHolder.thumbnail = row!!.findViewById(R.id.thumbnail_image)
            viewHolder.title = row!!.findViewById((R.id.thumbnail_title))

            row.setTag(viewHolder)
        } else {
            viewHolder = row.getTag() as ViewHolder
        }

        val tn = thumbnailLayouts[position]
        viewHolder.thumbnail!!.setImageResource(tn.image)
        viewHolder.title!!.setText(tn.title)

        return row
    }


    class ViewHolder {
        var thumbnail : ImageView? = null
        var title : TextView? = null
    }

}