package com.example.todoapplication.data.remote

import com.example.todoapplication.data.local.TaskEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    //  Get Current User ID
    private fun getUserId(): String {
        return auth.currentUser?.uid ?: ""
    }

    //  Add task to Firestore (if id empty, generate UUID)
    fun addTaskToFirestore(task: TaskEntity) {
        val userId = getUserId()

        val taskId = if (task.id.isEmpty()) {
            UUID.randomUUID().toString()
        } else {
            task.id
        }

        val taskWithId = task.copy(id = taskId, userId = userId)

        db.collection("users")
            .document(userId)
            .collection("tasks")
            .document(taskId)
            .set(taskWithId)
            .addOnSuccessListener {
                // Optional: Log success or handle UI update
            }
            .addOnFailureListener {
                // Optional: Log failure
            }
    }

    // Delete task from Firestore using taskId
    fun deleteTaskFromFirestore(taskId: String) {
        val userId = getUserId()

        db.collection("users")
            .document(userId)
            .collection("tasks")
            .document(taskId)
            .delete()
            .addOnSuccessListener {
                // Optional: Handle success
            }
            .addOnFailureListener {
                // Optional: Handle error
            }
    }
}
