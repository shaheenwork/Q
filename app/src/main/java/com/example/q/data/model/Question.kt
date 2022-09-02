package com.example.q.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "questionsTable", indices = [Index(value = ["id"], unique = true)])
data class Question(
    val type: Int,
    val file_name: String,
    val question: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val level: Int,
    val correctAnswer: String,
    var status: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)