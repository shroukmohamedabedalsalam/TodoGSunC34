package com.route.todoappgsun.database.model

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

//tables

@Entity
data class Task (
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id:Int?=null,
    @ColumnInfo var title:String?=null,
    @ColumnInfo var description:String?=null,
    @ColumnInfo var isCompleted:Boolean?=false
)