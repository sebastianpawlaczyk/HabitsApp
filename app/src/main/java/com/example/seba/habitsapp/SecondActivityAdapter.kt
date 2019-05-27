package com.example.seba.habitsapp

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class SecondActivityAdapter(var mCtx: Context, var resource: Int, var items: List<Model>,
                            var dbHelper: DBHelper)
    : ArrayAdapter<Model>(mCtx, resource, items) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View { dbHelper

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)

        val textView: TextView = view.findViewById(R.id.habit_name)
        val textView1: TextView = view.findViewById(R.id.habit_counter)
        val textView2: TextView = view.findViewById(R.id.habit_goal)
        val pb: ProgressBar = view.findViewById(R.id.progressBar)

        var myItems: Model = items[position]

        textView.text = myItems.title
        textView1.text = "Counter: " + myItems.counter.toString()
        textView2.text = "Goal:" + myItems.goal.toString()
        val prog: Float = myItems.counter.toFloat() / myItems.goal.toFloat() * 100
        val prog2: Int = prog.toInt()
        System.out.println("PROG!!!!-> " + prog)
        pb.setProgress(prog2)

        return view
    }
}