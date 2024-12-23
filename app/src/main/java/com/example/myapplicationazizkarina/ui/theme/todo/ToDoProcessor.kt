package com.example.myapplicationazizkarina.ui.theme.todo

import com.example.myapplicationazizkarina.actions.ToDoAction
import com.example.myapplicationazizkarina.repository.ToDoRepository
import com.example.myapplicationazizkarina.results.ToDoResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ToDoProcessor(private val repository: ToDoRepository) {
    fun process(action: ToDoAction): Flow<ToDoResult> = flow {
        when (action) {
            is ToDoAction.LoadTasks -> {
                emit(ToDoResult.Loading)
                emit(ToDoResult.Success(repository.getTasks()))
            }
            is ToDoAction.AddTask -> {
                if (action.title.isBlank()) {
                    emit(ToDoResult.Error("Название задачи не может быть пустым"))
                } else {
                    repository.addTask(action.title)
                    emit(ToDoResult.Success(repository.getTasks()))
                }
            }
            is ToDoAction.ToggleTask -> {
                repository.toggleTask(action.id)
                emit(ToDoResult.Success(repository.getTasks()))
            }
            is ToDoAction.DeleteTask -> {
                repository.deleteTask(action.id)
                emit(ToDoResult.Success(repository.getTasks()))
            }
        }
    }
}
