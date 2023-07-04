package com.ibrahimcanerdogan.roomtutorial.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.roomtutorial.database.Subscriber
import com.ibrahimcanerdogan.roomtutorial.repository.SubscriberRepository
import com.ibrahimcanerdogan.roomtutorial.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriberViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete : Subscriber

    val subscribers = repository.subscribers

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

    val buttonSaveUpdateText = MutableLiveData<String>()
    val buttonClearAllOrDeleteText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusMessage

    init {
        buttonSaveUpdateText.value = "Save"
        buttonClearAllOrDeleteText.value = "Clear All"
    }

    fun actionSaveOrUpdate() {
        if (isUpdateOrDelete) {
            subscriberToUpdateOrDelete.name = inputName.value!!
            subscriberToUpdateOrDelete.email = inputEmail.value!!

            updateSubscriber(subscriberToUpdateOrDelete)
        } else {
            val name = inputName.value
            val email = inputEmail.value
            addSubscriber(Subscriber(0, name!!, email!!))
            inputName.value = ""
            inputEmail.value = ""
        }
    }

    fun actionClearAllOrDelete() {
        if (isUpdateOrDelete) deleteSubscriber(subscriberToUpdateOrDelete) else clearAllSubscriber()
    }

    private fun addSubscriber(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertSubscriber(subscriber)
            withContext(Dispatchers.Main) {
                statusMessage.value = Event("Subscriber Inserted Successfully!")
            }
        }
    }

    private fun updateSubscriber(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateSubscriber(subscriber)
        withContext(Dispatchers.Main) {
            inputName.value = ""
            inputEmail.value = ""
            isUpdateOrDelete = false
            // Button Names changed.
            buttonSaveUpdateText.value = "Save"
            buttonClearAllOrDeleteText.value = "Clear All"
            statusMessage.value = Event("Subscriber Updated Successfully!")
        }
    }

    private fun deleteSubscriber(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteSubscriber(subscriber)
        withContext(Dispatchers.Main) {
            inputName.value = ""
            inputEmail.value = ""
            isUpdateOrDelete = false
            // Button Names changed.
            buttonSaveUpdateText.value = "Save"
            buttonClearAllOrDeleteText.value = "Clear All"
            statusMessage.value = Event("Subscriber Deleted Successfully!")
        }
    }

    private fun clearAllSubscriber() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearAllSubscribers()
        withContext(Dispatchers.Main) {
            statusMessage.value = Event("All Subscribers Cleared Successfully!")
        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber

        // Button Names changed.
        buttonSaveUpdateText.value = "Update"
        buttonClearAllOrDeleteText.value = "Delete"
    }
}