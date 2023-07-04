package com.ibrahimcanerdogan.roomtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahimcanerdogan.roomtutorial.database.Subscriber
import com.ibrahimcanerdogan.roomtutorial.database.SubscriberDatabase
import com.ibrahimcanerdogan.roomtutorial.databinding.ActivityMainBinding
import com.ibrahimcanerdogan.roomtutorial.repository.SubscriberRepository
import com.ibrahimcanerdogan.roomtutorial.view.SubscribeViewModelFactory
import com.ibrahimcanerdogan.roomtutorial.view.SubscriberAdapter
import com.ibrahimcanerdogan.roomtutorial.view.SubscriberViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter : SubscriberAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val factory = SubscribeViewModelFactory(repository)

        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.viewModel = subscriberViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        // Show alert message
        subscriberViewModel.message.observe(this) {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewSubscriber.layoutManager = LinearLayoutManager(this)
        adapter = SubscriberAdapter{ selectedSubscriber: Subscriber ->
            showSubscriberItemInfo(selectedSubscriber)
        }
        binding.recyclerViewSubscriber.adapter = adapter
        displaySubscribeList()
    }

    private fun displaySubscribeList() {
        subscriberViewModel.subscribers.observe(this) {
            Log.i("TAG", it.toString())
            adapter.setSubscriberList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun showSubscriberItemInfo(subscriber: Subscriber) {
        /*Toast
            .makeText(
                this,
                "Selected Subscriber \nName: ${subscriber.name} \nSubscriber Email: ${subscriber.email}",
                Toast.LENGTH_LONG
            )
            .show()*/

        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}