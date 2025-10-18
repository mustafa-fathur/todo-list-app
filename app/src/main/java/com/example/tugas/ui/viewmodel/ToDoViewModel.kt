package com.example.tugas.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.tugas.ui.data.ToDo
import java.util.Date
import java.util.UUID

class ToDoViewModel : ViewModel() {
    private val _todos = mutableStateListOf<ToDo>()
    val todos: List<ToDo> get() = _todos

    fun addTodo(title: String, description: String, date: Date) {
        val newTodo = ToDo(
            id = UUID.randomUUID().toString(),
            title = title,
            description = description,
            date = date
        )
        _todos.add(0, newTodo)
    }

    fun getTodoById(id: String): ToDo? = _todos.find { it.id == id }

    fun toggleDone(id: String) {
        val idx = _todos.indexOfFirst { it.id == id }
        if (idx >= 0) {
            val t = _todos[idx]
            _todos[idx] = t.copy(isDone = !t.isDone)
        }
    }

    fun removeToDo(id: String) {
        _todos.removeAll { it.id == id}
    }
}