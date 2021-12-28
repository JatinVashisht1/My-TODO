package com.example.mytodo.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.mytodo.domain.model.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao{

    @Query("SELECT * FROM todo")
    fun getAllToDo() : Flow<List<ToDoEntity>>

    @Query("SELECT * FROM todo WHERE id =:id")
    suspend fun getToDoById(id: Int) : ToDoEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insertToDo(toDoEntity: ToDoEntity)

    @Delete
    suspend fun deleteToDo(toDoEntity: ToDoEntity)

    @Query("DELETE FROM todo WHERE isCompleted")
    suspend fun deleteCompletedToDo()
}