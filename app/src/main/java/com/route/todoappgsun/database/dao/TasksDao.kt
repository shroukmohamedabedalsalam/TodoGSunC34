package com.route.todoappgsun.database.dao

import androidx.room.*
import com.route.todoappgsun.database.model.Task

// Data access object
// operations Task
@Dao
interface TasksDao {

    // add task
    // update task
    //delete task
    // get all tasks
    // search for task

    @Insert
    fun addTask(task:Task)
    @Update
    fun updateTask(task:Task)
    @Delete
    fun deleteTask(task: Task)

    @Query("select * from Task")
    fun getAllTasks():List<Task>

    @Query("select * from Task where title like :word or description like:word ")
    fun searchForTasks(word:String):List<Task>

}