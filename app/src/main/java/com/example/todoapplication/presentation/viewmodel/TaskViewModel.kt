package com.example.todoapplication.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.data.local.TaskDatabase
import com.example.todoapplication.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = TaskDatabase.getDatabase(application).taskDao()

    // Fetch all tasks
    val allTasks: Flow<List<TaskEntity>> = dao.getAllTasks()

    // Insert task
    fun addTask(task: TaskEntity) {
        viewModelScope.launch {
            dao.insertTask(task)
        }
    }

    // Delete task
    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            dao.deleteTask(task)
        }
    }
}
