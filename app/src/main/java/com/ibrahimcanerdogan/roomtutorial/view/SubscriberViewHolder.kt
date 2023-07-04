package com.ibrahimcanerdogan.roomtutorial.view

import androidx.recyclerview.widget.RecyclerView
import com.ibrahimcanerdogan.roomtutorial.database.Subscriber
import com.ibrahimcanerdogan.roomtutorial.databinding.ItemSubscriberBinding

class SubscriberViewHolder(
    val binding: ItemSubscriberBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriber: Subscriber, clickListener : (Subscriber) -> Unit) {
        binding.apply {
            textViewSubscriberName.text = subscriber.name
            textViewSubscriberEmail.text = subscriber.email
            cardSubscriber.setOnClickListener {
                clickListener(subscriber)
            }
        }
    }
}