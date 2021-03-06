package com.example.seba.habitsapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "HABITSDB.db"
    }

    //Table
    private val TABLE_NAME = "Habit"
    private val COL_TITLE = "Title"
    private val COL_DESC = "Desc"
    private val COL_PHOTO = "Photo"
    private val COL_COUNTER = "Counter"
    private val COL_GOAL = "Goal"

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COL_TITLE TEXT, $COL_DESC TEXT, " +
                "$COL_PHOTO INTEGER, $COL_COUNTER INTEGER, $COL_GOAL INTEGER)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD
    val allHabits:List<Model>
    get(){
        val habitList = ArrayList<Model>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst())
        {
            do {
                val habit = Model()
                habit.title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                habit.desc = cursor.getString(cursor.getColumnIndex(COL_DESC))
                habit.photo = cursor.getInt(cursor.getColumnIndex(COL_PHOTO))
                habit.counter = cursor.getInt(cursor.getColumnIndex(COL_COUNTER))
                habit.goal = cursor.getInt(cursor.getColumnIndex(COL_GOAL))

                habitList.add(habit)
            }while (cursor.moveToNext())
        }
        db.close()
        return habitList
    }

    fun addHabit(habit:Model) {
        for(habit_db in allHabits){
            if(habit_db.title == habit.title) {
                return
            }
        }
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_TITLE, habit.title)
        values.put(COL_DESC, habit.desc)
        values.put(COL_PHOTO, habit.photo)
        values.put(COL_COUNTER, habit.counter)
        values.put(COL_GOAL, habit.goal)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateHabit(habit: Model):Int{

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_TITLE, habit.title)
        values.put(COL_DESC, habit.desc)
        values.put(COL_PHOTO, habit.photo)
        values.put(COL_COUNTER, habit.counter)
        values.put(COL_GOAL, habit.goal)

        return db.update(TABLE_NAME, values, "$COL_TITLE=?", arrayOf(habit.title.toString()))
    }

    fun deletedHabit(habit: Model){

        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COL_TITLE=?", arrayOf(habit.title.toString()))
        db.close()
    }
}