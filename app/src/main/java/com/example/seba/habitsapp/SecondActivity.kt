package com.example.seba.habitsapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {

    private lateinit var drawer_layout: DrawerLayout
    private lateinit var mToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


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
        drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout_second)
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
