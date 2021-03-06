package com.example.seba.habitsapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_dialog.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var db: DBHelper
    private lateinit var drawer_layout: DrawerLayout
    private lateinit var mToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //dbHelper
        db = DBHelper(this)

        //listview
        var listview = findViewById<ListView>(R.id.listView)

        var list = mutableListOf<Model>()

        //add items in listview
        db.addHabit(Model("Drink water", "Water is Your Best Friend for Life.", R.drawable.img_drop, 100))
        db.addHabit(Model("Workout", "Respect your body. It’s the only one you get.", R.drawable.fitenss, 100))
        db.addHabit(Model("Learn", "Life is a journey, not a destination.", R.drawable.learn, 100))

        //loop for adding all habits from db
        for(habit in db.allHabits){
            list.add(habit)
        }

        //update adapter!!!!!!
        listview.adapter = MyListAdapter(this, R.layout.row, list, db)


        floating_action_button.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("Add new habit")
            val mAlertDialog = mBuilder.show()
            mDialogView.add_habit_button.setOnClickListener {
                mAlertDialog.dismiss()

                var habit = mDialogView.edit_habit.text.toString()
                var info = mDialogView.edit_info.text.toString()
                var goal = mDialogView.edit_goal.text.toString().toIntOrNull()
                if (habit.isEmpty())
                {
                    habit = "NoName"
                }
                if (info.isEmpty())
                {
                    info = "NoInfo"
                }
                if (goal == null)
                {
                    goal = 100;
                }
                System.out.println("!!!!!!!!" + goal)

                list.add(Model(habit, info, R.drawable.question, goal))
                db.addHabit(Model(habit, info, R.drawable.question, goal))
                listview.adapter = MyListAdapter(this, R.layout.row, list, db)
            }

            mDialogView.cancel_button.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        //navigation_view
        val nav_view: NavigationView = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener { menuItem ->

            if (menuItem.itemId == R.id.myHabits) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            if (menuItem.itemId == R.id.stats) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }

            if (menuItem.itemId == R.id.someAction) {
                val intent = Intent(this, ThirdActivity::class.java)
                startActivity(intent)
            }
            true
        }

        //buttons for nav_view
        drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout)
        mToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)

        drawer_layout.addDrawerListener(mToggle)
        mToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (mToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
