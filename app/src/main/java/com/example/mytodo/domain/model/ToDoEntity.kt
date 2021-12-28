package com.example.mytodo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDoEntity(
    val isCompleted: Boolean = false,
    val task: String = "",
    val detail: String = "",
    val pin: Boolean = false,
    val timeStamp: Long = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
){

}
