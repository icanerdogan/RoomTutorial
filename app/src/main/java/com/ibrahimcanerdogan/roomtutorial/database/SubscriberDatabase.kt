package com.ibrahimcanerdogan.roomtutorial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {

    abstract val subscriberDao : SubscriberDao

    companion object {
        // The @Volatile annotation is typically used when working with multi-threaded code where multiple threads might access and modify the same property concurrently.
        // By marking a property as volatile, you can ensure that changes made by one thread are visible to other threads immediately, without relying on thread synchronization mechanisms like locks or explicit memory barriers.
        @Volatile
        private var INSTANCE : SubscriberDatabase? = null
        fun getInstance(context: Context) : SubscriberDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriberdatabase"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}