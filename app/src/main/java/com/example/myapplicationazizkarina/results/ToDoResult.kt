package com.example.myapplicationazizkarina.results

import com.example.myapplicationazizkarina.model.ToDoItem

sealed class ToDoResult {
    object Loading : ToDoResult()
    data class Success(val tasks: List<ToDoItem>) : ToDoResult()
    data class Error(val message: String) : ToDoResult()
}

