package com.route.todoappgsun.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.todoappgsun.database.dao.TasksDao
import com.route.todoappgsun.database.model.Task

// represent database

@Database(entities = [Task::class],version = 1,exportSchema = false)
abstract class TasksDataBase : RoomDatabase(){

    abstract fun tasksDao():TasksDao // annotation proccessor

    //data object

    // signelton -> class -> construct single object
    // companion object
    companion object{
         private var database:TasksDataBase?=null  // inaccessable
        private val DATABASE_NAME = "tasks-db";
        fun getInstance(context: Context):TasksDataBase{
            if(database==null){// first
                // create
                database =  Room.databaseBuilder(context,
                    TasksDataBase::class.java, DATABASE_NAME)
                     .fallbackToDestructiveMigration()// provide migration
                     .allowMainThreadQueries()// allow accessing DB in ui thread
                        // kotlin coroutines
                     .build()

            }

            return database!!
        }
    }


}

