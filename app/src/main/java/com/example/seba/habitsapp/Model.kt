package com.example.seba.habitsapp

import java.util.*

class Model
{
    var title:String? = null
    var desc:String? = null
    var photo:Int = 0
    var counter: Int = 0
    var goal: Int = 0

    constructor(){}

    constructor(title: String, desc: String, photo: Int, goal: Int) {
        this.title = title
        this.desc = desc
        this.photo = photo
        this.goal = goal
    }
}