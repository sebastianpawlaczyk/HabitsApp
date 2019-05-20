package com.example.seba.habitsapp

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class SecondActivityAdapter(var mCtx: Context, var resource: Int, var items: List<Model>,
                            var dbHelper: DBHelper)
    : ArrayAdapter<Model>(mCtx, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View { dbHelper

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)

        val textView: TextView = view.findViewById(R.id.habit_name)
        val textView1: TextView = view.findViewById(R.id.habit_counter)

        var myItems: Model = items[position]

        textView.text = myItems.title
        textView1.text = myItems.counter.toString()

        return view
    }
}