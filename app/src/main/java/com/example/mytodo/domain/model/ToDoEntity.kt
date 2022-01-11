package com.example.mytodo.domain.model

import android.icu.util.Calendar
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.time.LocalDate
import java.util.*


@Entity(tableName = "todo")
data class ToDoEntity(
    val isCompleted: Boolean = false,
    val task: String = "",
    val detail: String = "",
    val pin: Boolean = false,
    val timeStamp: Long = 0,
    val deadLineTime: Int = 0,
    val deadLineDate: Long = LocalDate.now().toEpochDay(),
    val isWeekly: Boolean = false,
    val isMonthly: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
){
}
