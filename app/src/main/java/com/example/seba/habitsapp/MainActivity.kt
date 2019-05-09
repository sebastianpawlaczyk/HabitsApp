package com.example.seba.habitsapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
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
        list.add(Model("Drink water", "Water is Your Best Friend for Life.", R.drawable.img_drop))
        list.add(Model("Workout", "Respect your body. It’s the only one you get.", R.drawable.fitenss))
        list.add(Model("Learn", "Life is a journey, not a destination.", R.drawable.learn))

        //adapter
        listview.adapter = MyListAdapter(this, R.layout.row, list)

        //listview item clicks
        listview.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                Toast.makeText(this@MainActivity, "Item onc clicked", Toast.LENGTH_SHORT).show()
            }

            if (position == 1) {
                Toast.makeText(this@MainActivity, "Item onc clicked", Toast.LENGTH_SHORT).show()
            }

            if (position == 2) {
                Toast.makeText(this@MainActivity, "Item onc clicked", Toast.LENGTH_SHORT).show()
            }
        }

        floating_action_button.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("Add new habit")
            val mAlertDialog = mBuilder.show()
            mDialogView.add_habit_button.setOnClickListener {
                mAlertDialog.dismiss()

                val habit = mDialogView.edit_habit.text.toString()
                val info = mDialogView.edit_info.text.toString()

                list.add(Model(habit, info, R.drawable.question))
                db.addHabit(Model(habit, info, R.drawable.question))
                listview.adapter = MyListAdapter(this, R.layout.row, list)
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
                Toast.makeText(applicationContext, "someAction", Toast.LENGTH_SHORT).show()
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
