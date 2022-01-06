package com.example.mytodo.presentation.add_edit_to_do_screen

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.MyAlarmService
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.domain.use_cases.to_do_use_cases.ToDoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.sql.Time
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class AddEditToDoViewModel @Inject constructor(
    private val useCases: ToDoUseCases,
    private val savedStateHandle: SavedStateHandle,
//    @ApplicationContext private val applicationContext: Context
) : ViewModel() {
    private val _noteState = mutableStateOf<ToDoEntity>(ToDoEntity())
    val noteState = _noteState

    val updatedNote = _noteState

    init {
        savedStateHandle.get<Int>("id")?.let {
            if (it != -1) {
                viewModelScope.launch {
                    getToDoById(id = it)
                }
            }
        }
    }

    private suspend fun getToDoById(id: Int) {
        _noteState.value = useCases.getToDoById(id = id)!!
    }

    suspend fun insertToDo(toDoEntity: ToDoEntity) {
        useCases.insertToDo(toDoEntity = toDoEntity)
    }

    @ExperimentalMaterialApi
    suspend fun save(context: Context) {
//        if (!updatedNote.value.task.isNullOrEmpty()) {
        useCases.insertToDo(
            ToDoEntity(
                isCompleted = updatedNote.value.isCompleted,
                task = updatedNote.value.task.trim(),
                detail = updatedNote.value.detail.trim(),
                id = _noteState.value.id,
                pin = updatedNote.value.pin,
                timeStamp = System.currentTimeMillis(),
                deadLineTime = updatedNote.value.deadLineTime,
                deadLineDate = updatedNote.value.deadLineDate,
                isWeekly = updatedNote.value.isWeekly,
                isMonthly = updatedNote.value.isMonthly,
            )
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MyAlarmService::class.java)
//        alarmManager.setTime(updatedNote.value.deadLineTime/10.0.pow(6).toLong())

        val calendar = Calendar.getInstance()
        calendar.set(
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH,
            14,
            0
        )


        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent , 0)
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, 0,pendingIntent )
//        Log.d("HomeScreen", "Time set for alarm is: " + LocalTime.ofSecondOfDay(updatedNote.value.deadLineTime/10.0.pow(9).toLong()).toString())
    }
}