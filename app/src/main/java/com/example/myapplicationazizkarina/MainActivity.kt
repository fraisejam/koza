package com.example.myapplicationazizkarina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationazizkarina.ui.theme.MyApplicationazizkarinaTheme



// Модель задачи
data class ToDoItem(val id: Int, val title: String, var isCompleted: Boolean)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ToDoApp()
                }
            }
        }
    }
}

@Composable
fun ToDoApp() {
    // Состояние для хранения списка задач
    var tasks by remember { mutableStateOf(listOf<ToDoItem>()) }
    var newTaskTitle by remember { mutableStateOf("") }

    // Функция для добавления задачи
    fun addTask() {
        if (newTaskTitle.isNotBlank()) {
            tasks = tasks + ToDoItem(id = tasks.size, title = newTaskTitle, isCompleted = false)
            newTaskTitle = ""  // Очищаем поле ввода после добавления задачи
        }
    }

    // Функция для переключения состояния задачи (выполнена/не выполнена)
    fun toggleTaskCompletion(id: Int) {
        tasks = tasks.map {
            if (it.id == id) it.copy(isCompleted = !it.isCompleted) else it
        }
    }

    // Функция для удаления задачи
    fun deleteTask(id: Int) {
        tasks = tasks.filter { it.id != id }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Поле для ввода новой задачи
        Row(modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                modifier = Modifier.weight(1f).padding(8.dp),
                decorationBox = { innerTextField ->
                    if (newTaskTitle.isEmpty()) {
                        Text("Enter task...", color = MaterialTheme.colorScheme.onBackground)
                    }
                    innerTextField()
                }
            )
            IconButton(onClick = { addTask() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }

        // Список задач
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tasks) { task ->
                TaskItem(task = task, onToggle = { toggleTaskCompletion(task.id) }, onDelete = { deleteTask(task.id) })
            }
        }
    }
}

@Composable
fun TaskItem(task: ToDoItem, onToggle: () -> Unit, onDelete: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Checkbox(checked = task.isCompleted, onCheckedChange = { onToggle() })
        Text(
            text = task.title,
            modifier = Modifier.weight(1f).padding(start = 8.dp),
            style = MaterialTheme.typography.body1
        )
        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Task")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoListTheme {
        ToDoApp()
    }
}
