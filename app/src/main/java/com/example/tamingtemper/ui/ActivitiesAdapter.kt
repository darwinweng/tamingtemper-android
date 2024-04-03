package com.example.tamingtemper.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tamingtemper.databinding.ItemLayoutActivityBinding
import com.example.tamingtemper.vo.Activity

class ActivitiesAdapter: ListAdapter<Activity, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLayoutActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder)
        val data = getItem(position)
        holder.binding.apply {
            lblTitle.text = data?.title
        }
    }
    class ViewHolder(val binding: ItemLayoutActivityBinding): RecyclerView.ViewHolder(binding.root)
    companion object {
        val TAG: String = "TicketAdapter"
        val COMPARATOR = object : DiffUtil.ItemCallback<Activity>() {
            override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
                return oldItem == newItem
            }
        }
    }
}