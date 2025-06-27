package com.example.todoapplication.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.data.local.TaskDatabase
import com.example.todoapplication.data.local.TaskEntity
import com.example.todoapplication.data.remote.FirestoreRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    // Room DAO
    private val dao = TaskDatabase.getDatabase(application).taskDao()

    // Firestore repository
    private val firestoreRepository = FirestoreRepository()

    // Current user ID
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    // Flow of all tasks for this user
    val allTasks: Flow<List<TaskEntity>> = dao.getAllTasks(userId)

    // Add Task (both to Room and Firestore)
    fun addTask(task: TaskEntity) {
        viewModelScope.launch {
            // Generate ID if empty
            val taskId = if (task.id.isEmpty()) {
                UUID.randomUUID().toString()
            } else {
                task.id
            }

            val taskWithUser = task.copy(id = taskId, userId = userId)

            // Save to Room
            dao.insertTask(taskWithUser)

            // Save to Firestore
            firestoreRepository.addTaskToFirestore(taskWithUser)
        }
    }

    // Delete Task (both from Room and Firestore)
    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            dao.deleteTask(task)
            firestoreRepository.deleteTaskFromFirestore(task.id)
        }
    }
}
