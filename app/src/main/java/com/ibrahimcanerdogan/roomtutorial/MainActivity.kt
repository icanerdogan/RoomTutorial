package com.ibrahimcanerdogan.roomtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ibrahimcanerdogan.roomtutorial.database.SubscriberDatabase
import com.ibrahimcanerdogan.roomtutorial.databinding.ActivityMainBinding
import com.ibrahimcanerdogan.roomtutorial.repository.SubscriberRepository
import com.ibrahimcanerdogan.roomtutorial.view.SubscribeViewModelFactory
import com.ibrahimcanerdogan.roomtutorial.view.SubscriberViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val factory = SubscribeViewModelFactory(repository)

        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.viewModel = subscriberViewModel
        binding.lifecycleOwner = this

        displaySubscribeList()
    }

    private fun displaySubscribeList() {
        subscriberViewModel.subscribers.observe(this) {
            Log.i("TAG", it.toString())
        }
    }
}