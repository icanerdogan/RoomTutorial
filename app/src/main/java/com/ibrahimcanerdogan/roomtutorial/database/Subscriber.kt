package com.ibrahimcanerdogan.roomtutorial.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber")
data class Subscriber(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_id")
    val id : Int,
    @ColumnInfo(name = "subscriber_name")
    var name : String,
    @ColumnInfo(name = "subscriber_email")
    var email : String
)