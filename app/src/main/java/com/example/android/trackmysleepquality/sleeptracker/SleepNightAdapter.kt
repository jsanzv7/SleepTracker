/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding
import com.example.android.trackmysleepquality.sleeptracker.SleepNightAdapter.ViewHolder.Companion.from

/* The ViewHolder class is a subclass of RecyclerView.ViewHolder. It holds a reference to the binding
object for each item in the RecyclerView */
class SleepNightAdapter(val clickListener: SleepNightListener) : ListAdapter<SleepNight,
        SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: SleepNightListener, item: SleepNight) {
            binding.sleep = item
            /* The clickListener is passed as a parameter to the bind() function. The getItem()
            function is called on the adapter to get the SleepNight object at the position that was
            clicked. The !! operator is used to ensure that the SleepNight object is not null. */
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        /* The bind() function is called on the ViewHolder object. The clickListener is passed as a
        parameter to the bind() function. The getItem() function is called on the adapter to get the
        SleepNight object at the position that was clicked. The !! operator is used to ensure that
        the
        SleepNight object is not null. */
        holder.bind(clickListener,getItem(position)!!)
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }
}

/* "SleepNightListener is a class that takes a lambda as a constructor parameter and calls that lambda
when onClick is called."

The SleepNightListener class is a simple class that takes a lambda as a constructor parameter and
calls that lambda when onClick is called */
class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
    /**
     * The function takes a SleepNight object as a parameter and returns a function that takes a Long
     * as a parameter and returns Unit
     *
     * @param night SleepNight - The SleepNight object that was clicked.
     */
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}