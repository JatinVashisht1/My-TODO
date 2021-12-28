package com.example.mytodo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytodo.domain.model.ToDoEntity

@Database(entities = [ToDoEntity::class], version = 1)
abstract class ToDoDatabase : RoomDatabase(){
    abstract val todoDao: ToDoDao
    companion object{
        const val DATABASE_NAME = "todo_db"
    }
}