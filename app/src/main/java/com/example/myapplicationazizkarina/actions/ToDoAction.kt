package com.example.myapplicationazizkarina.actions

sealed class ToDoAction {
    object LoadTasks : ToDoAction()
    data class AddTask(val title: String) : ToDoAction()
    data class ToggleTask(val id: Int) : ToDoAction()
    data class DeleteTask(val id: Int) : ToDoAction()
}
