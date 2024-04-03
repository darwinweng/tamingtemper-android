package com.example.tamingtemper.vo

data class Level(
    val level: String? = "",
    val title: String? = "",
    val description: String? = "",
    val state: String? = "",
    val activities: List<Activity>? = arrayListOf()
)
