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
    val deadLineTime: Long = 0,
    val deadLineDate: Long = 0,
    val isWeekly: Boolean = false,
    val isMonthly: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
){

}
