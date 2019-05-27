package com.example.seba.habitsapp

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.approval.view.*
import kotlinx.android.synthetic.main.layout_dialog.view.*

class MyListAdapter(var mCtx: Context, var resource: Int, var items: MutableList<Model>,
                    var dbHelper: DBHelper)
    : ArrayAdapter<Model>(mCtx, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View { dbHelper

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)

        val button_del: Button = view.findViewById(R.id.button_del)
        val button_done: Button = view.findViewById(R.id.button_done)
        val imageView: ImageView = view.findViewById(R.id.iconIv)
        val textView: TextView = view.findViewById(R.id.titleTv)
        val textView1: TextView = view.findViewById(R.id.descTv)

        var myItems: Model = items[position]

        imageView.setImageDrawable(mCtx.resources.getDrawable(myItems.photo))
        textView.text = myItems.title
        textView1.text = myItems.desc


        button_done.setOnClickListener(){
            var item = items[position]
            item.counter++
            dbHelper.updateHabit(item)

            for(habit in dbHelper.allHabits){
                if(habit.title == item.title)
                    System.out.println(habit.counter)
            }
        }

        button_del.setOnClickListener(){
            val mDialogView = LayoutInflater.from(mCtx).inflate(R.layout.approval, null)
            val mBuilder = AlertDialog.Builder(mCtx)
                    .setView(mDialogView)
                    .setTitle("Are you sure?")
            val mAlertDialog = mBuilder.show()
            mDialogView.confirm_button.setOnClickListener {
                mAlertDialog.dismiss()

                dbHelper.deletedHabit(items[position])
                items.removeAt(position)
                System.out.println("Position = " + position)
                notifyDataSetChanged()
            }

            mDialogView.cancer_button.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
        return view
    }
}