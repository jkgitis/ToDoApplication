package com.example.todoapplication.data.remote

import android.util.Log
import com.example.todoapplication.data.local.TaskEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val userId: String
        get() = auth.currentUser?.uid ?: "unknown"

    // 🔥 Function to Add Task to Firestore
    fun addTaskToFirestore(task: TaskEntity) {
        val taskId = UUID.randomUUID().toString()

        val taskMap = hashMapOf(
            "id" to taskId,
            "title" to task.title,
            "description" to task.description,
            "userId" to userId
        )

        db.collection("tasks")
            .document(taskId)
            .set(taskMap)
            .addOnSuccessListener {
                Log.d("Firestore", "Task added to Firestore")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding task", e)
            }
    }

    // 🔥 Function to Delete Task from Firestore
    fun deleteTaskFromFirestore(taskId: String) {
        db.collection("tasks")
            .document(taskId)
            .delete()
            .addOnSuccessListener {
                Log.d("Firestore", "Task deleted from Firestore")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error deleting task", e)
            }
    }
}
