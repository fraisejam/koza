package com.example.myapplicationazizkarina.ui.theme.todo

import com.example.myapplicationazizkarina.model.ToDoItem

data class ToDoState(
    val isLoading: Boolean = false,
    val tasks: List<ToDoItem> = emptyList(),
    val error: String? = null
)
