package com.example.myapplicationazizkarina.ui.theme.todo

sealed class ToDoIntent {
    object LoadTasks : ToDoIntent()
    data class AddTask(val title: String) : ToDoIntent()
    data class ToggleTask(val id: Int) : ToDoIntent()
    data class DeleteTask(val id: Int) : ToDoIntent()
}
