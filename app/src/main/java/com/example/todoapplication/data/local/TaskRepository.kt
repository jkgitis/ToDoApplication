package com.example.todoapplication.data.local

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    val allTasks: Flow<List<TaskEntity>> = dao.getAllTasks()

    suspend fun insert(task: TaskEntity) {
        dao.insertTask(task)
    }

    suspend fun update(task: TaskEntity) {
        dao.updateTask(task)
    }

    suspend fun delete(task: TaskEntity) {
        dao.deleteTask(task)
    }
}
