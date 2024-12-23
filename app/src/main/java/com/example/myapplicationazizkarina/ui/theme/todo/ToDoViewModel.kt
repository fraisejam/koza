package com.example.myapplicationazizkarina.ui.theme.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationazizkarina.actions.ToDoAction
import com.example.myapplicationazizkarina.results.ToDoResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ToDoViewModel(private val processor: ToDoProcessor) : ViewModel() {
    private val _state = MutableStateFlow(ToDoState())
    val state: StateFlow<ToDoState> get() = _state

    fun handleIntent(intent: ToDoIntent) {
        when (intent) {
            is ToDoIntent.LoadTasks -> sendAction(ToDoAction.LoadTasks)
            is ToDoIntent.AddTask -> sendAction(ToDoAction.AddTask(intent.title))
            is ToDoIntent.ToggleTask -> sendAction(ToDoAction.ToggleTask(intent.id))
            is ToDoIntent.DeleteTask -> sendAction(ToDoAction.DeleteTask(intent.id))
        }
    }

    private fun sendAction(action: ToDoAction) {
        viewModelScope.launch {
            processor.process(action).collect { result ->
                when (result) {
                    is ToDoResult.Loading -> _state.value = _state.value.copy(isLoading = true)
                    is ToDoResult.Success -> _state.value = _state.value.copy(isLoading = false, tasks = result.tasks, error = null)
                    is ToDoResult.Error -> _state.value = _state.value.copy(isLoading = false, error = result.message)
                }
            }
        }
    }
}

