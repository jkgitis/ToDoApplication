package com.example.todoapplication.data.local

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    private val userId: String
        get() = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    // Fetch tasks for the current user
    val allTasks: Flow<List<TaskEntity>>
        get() = dao.getAllTasks(userId)

    // Insert task for the current user
    suspend fun insert(task: TaskEntity) {
        dao.insertTask(task.copy(userId = userId))
    }

    // Delete task
    suspend fun delete(task: TaskEntity) {
        dao.deleteTask(task)
    }

    // @Update
    // suspend fun updateTask(task: TaskEntity)
    //
    // Then enable this:
    // suspend fun update(task: TaskEntity) {
    //     dao.updateTask(task)
    // }
}
