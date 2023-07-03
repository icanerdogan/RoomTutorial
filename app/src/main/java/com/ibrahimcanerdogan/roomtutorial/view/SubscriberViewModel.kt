package com.ibrahimcanerdogan.roomtutorial.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.roomtutorial.database.Subscriber
import com.ibrahimcanerdogan.roomtutorial.repository.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubscriberViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    val subscribers = repository.subscribers

    val inputName = MutableLiveData<String>()
    val inputNameValue : LiveData<String> = inputName

    val inputEmail = MutableLiveData<String>()
    val inputEmailValue : LiveData<String> = inputEmail

    val buttonSaveUpdateText = MutableLiveData<String>()
    val buttonClearAllOrDeleteText = MutableLiveData<String>()

    init {
        buttonSaveUpdateText.value = "Save"
        buttonClearAllOrDeleteText.value = "Clear All"
    }

    fun actionSaveOrUpdate() {
        val name = inputName.value
        val email = inputEmail.value
        addSubscriber(Subscriber(0, name!!, email!!))
        inputName.value = ""
        inputEmail.value = ""
    }

    fun actionClearAllOrDelete() {
        clearAllSubscriber()
    }

    fun addSubscriber(subscriber: Subscriber) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertSubscriber(subscriber)
        }
    }

    fun updateSubscriber(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateSubscriber(subscriber)
    }

    fun deleteSubscriber(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteSubscriber(subscriber)
    }

    fun clearAllSubscriber() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearAllSubscribers()
    }
}