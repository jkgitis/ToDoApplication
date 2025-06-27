package com.example.todoapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    val id: String = "",   // Unique ID (match with Firestore ID if needed)
    val title: String,
    val description: String,
    val userId: String     // Critical for user-specific tasks
)
