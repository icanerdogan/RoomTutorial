package com.ibrahimcanerdogan.roomtutorial.repository

import com.ibrahimcanerdogan.roomtutorial.database.Subscriber
import com.ibrahimcanerdogan.roomtutorial.database.SubscriberDao

class SubscriberRepository(
    private val dao : SubscriberDao
) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insertSubscriber(subscriber: Subscriber) = dao.insertSubscriber(subscriber)

    suspend fun updateSubscriber(subscriber: Subscriber) = dao.updateSubscriber(subscriber)

    suspend fun deleteSubscriber(subscriber: Subscriber) = dao.deleteSubscriber(subscriber)

    suspend fun clearAllSubscribers(subscriber: Subscriber) = dao.clearAllSubscribers()

}