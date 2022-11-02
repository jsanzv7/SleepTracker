package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

/* The ViewHolder class is a subclass of RecyclerView.ViewHolder. It holds a reference to the binding
object for each item in the RecyclerView */
class SleepNightAdapter : ListAdapter<SleepNight,
        SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()){


    /**
     * I/* Getting the item at the position in the list. */
        t binds the data to the view holder.
     *
     * @param holder ViewHolder - The ViewHolder which should be updated to represent the contents of
     * the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item =getItem(position)

       /* It binds the data to the view holder. */
         holder.bind(item)
    }

    /**
     * The function returns a ViewHolder object, which is created by calling the from() function on the
     * ViewHolder class
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an
     * adapter position.
     * @param viewType The view type of the new View.
     * @return ViewHolder.from(parent)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /* Creating a new ViewHolder object and returning it. */
        return ViewHolder.from(parent)
    }

    /* It's a class that holds a reference to a ListItemSleepNightBinding object */
    class ViewHolder private constructor(val binding: ListItemSleepNightBinding)
        : RecyclerView.ViewHolder(binding.root){


        /**
         * T/* It's setting the sleep variable in the binding object to the item variable. */
            he function takes a SleepNight object as a parameter and binds it to the binding object
         *
         * @param item SleepNight - The item to bind to the view.
         */
        fun bind(item: SleepNight) {
            binding.sleep = item
            /* It's telling the binding object to execute any pending bindings. */
            binding.executePendingBindings()
        }

        companion object {
            /**
             * The function takes a parent ViewGroup as a parameter, inflates the layout, and returns a
             * ViewHolder
             *
             * @param parent ViewGroup - The ViewGroup into which the new View will be added after it
             * is bound to an adapter position.
             * @return A ViewHolder object.
             */
            fun from(parent: ViewGroup): ViewHolder {
                /* It's getting a LayoutInflater object from the parent ViewGroup. */
                val layoutInflater = LayoutInflater.from(parent.context)
                /* It's inflating the layout and returning a binding object. */
                /* It's creating a new ViewHolder object and returning it. */
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
/* SleepNightDiffCallback is a class that extends DiffUtil.ItemCallback<SleepNight> and overrides the
areItemsTheSame() and areContentsTheSame() methods */
class SleepNightDiffCallback :
    DiffUtil.ItemCallback<SleepNight>() {
    /**
     * If the item IDs are the same, then the items are the same
     *
     * @param oldItem The item in the old list.
     * @param newItem The new item in the updated list.
     * @return The return value is a boolean that indicates whether the two items represent the same
     * object or not.
     */
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        /* It's comparing the nightId of the old item with the nightId of the new item. */
        return oldItem.nightId == newItem.nightId
    }

    /**
     * I/* It's comparing the two items to see if they are the same. */
        f the two items are the same, return true
     *
     * @param oldItem The item in the old list.
     * @param newItem The new item in the list.
     * @return The return value is a boolean.
     */
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
