package com.ibrahimcanerdogan.roomtutorial.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibrahimcanerdogan.roomtutorial.repository.SubscriberRepository

class SubscribeViewModelFactory(
    private val repository: SubscriberRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            return SubscriberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}