package com.example.myapplicationazizkarina.repository

import com.example.myapplicationazizkarina.model.ToDoItem

class ToDoRepository {
    private val tasks = mutableListOf<ToDoItem>()
    private var currentId = 0

    fun getTasks(): List<ToDoItem> = tasks

    fun addTask(title: String) {
        tasks.add(ToDoItem(id = currentId++, title = title, isCompleted = false))
    }

    fun toggleTask(id: Int) {
        tasks.find { it.id == id }?.let {
            it.isCompleted = !it.isCompleted
        }
    }

    fun deleteTask(id: Int) {
        tasks.removeIf { it.id == id }
    }
}
