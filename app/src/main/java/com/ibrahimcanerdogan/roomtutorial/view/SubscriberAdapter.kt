package com.ibrahimcanerdogan.roomtutorial.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimcanerdogan.roomtutorial.R
import com.ibrahimcanerdogan.roomtutorial.database.Subscriber
import com.ibrahimcanerdogan.roomtutorial.databinding.ItemSubscriberBinding

class SubscriberAdapter(
    private val subscriberList : List<Subscriber>,
    private val clickListener : (Subscriber) -> Unit
) : RecyclerView.Adapter<SubscriberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemSubscriberBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_subscriber, parent, false)
        return SubscriberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }

    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        holder.bind(subscriberList[position], clickListener)
    }
}