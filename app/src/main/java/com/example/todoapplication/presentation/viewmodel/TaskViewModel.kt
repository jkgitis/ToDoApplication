package com.example.todoapplication.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.data.local.TaskDatabase
import com.example.todoapplication.data.local.TaskEntity
import com.example.todoapplication.data.local.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository

    val allTasks: StateFlow<List<TaskEntity>>

    init {
        val dao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(dao)

        allTasks = repository.allTasks
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }

    fun addTask(task: TaskEntity) = viewModelScope.launch {
        repository.insert(task)
    }

    fun updateTask(task: TaskEntity) = viewModelScope.launch {
        repository.update(task)
    }

    fun deleteTask(task: TaskEntity) = viewModelScope.launch {
        repository.delete(task)
    }
}
