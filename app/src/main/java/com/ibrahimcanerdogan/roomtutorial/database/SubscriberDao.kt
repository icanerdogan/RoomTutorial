package com.ibrahimcanerdogan.roomtutorial.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubscriberDao {
    // All functions return a value, this value can be manipulated by checking it on the view model.
    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    @Query("DELETE FROM subscriber")
    suspend fun clearAllSubscribers() : Int

    @Query("SELECT * FROM subscriber")
    fun getAllSubscribers() : LiveData<List<Subscriber>>
}