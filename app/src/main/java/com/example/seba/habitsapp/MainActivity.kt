package com.example.seba.habitsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            if (position == 0)
            {
                Toast.makeText(this@MainActivity, "Item onc clicked", Toast.LENGTH_SHORT).show()
            }

            if (position == 1)
            {
                Toast.makeText(this@MainActivity, "Item onc clicked", Toast.LENGTH_SHORT).show()
            }

            if (position == 2)
            {
                Toast.makeText(this@MainActivity, "Item onc clicked", Toast.LENGTH_SHORT).show()
            }
        }

        floating_action_button.setOnClickListener {
            list.add(Model("Noname", "Halo halo.", R.drawable.question))
            listview.adapter = MyListAdapter(this, R.layout.row, list)
        }

    }
}
