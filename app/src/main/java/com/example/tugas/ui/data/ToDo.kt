package com.example.tugas.ui.data

import java.util.Date

data class ToDo(
    val id: String,
    var title: String,
    var description: String,
    var date: Date,
    var isDone: Boolean = false
)