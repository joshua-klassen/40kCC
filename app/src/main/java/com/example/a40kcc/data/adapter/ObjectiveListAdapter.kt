package com.example.a40kcc.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Objective

class ObjectiveListAdapter :
    ListAdapter<Objective, ObjectiveListAdapter.ObjectiveViewHolder>(ObjectiveComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectiveViewHolder {
        return ObjectiveViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ObjectiveViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class ObjectiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val objectiveItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            objectiveItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ObjectiveViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.data_object, parent, false)
                return ObjectiveViewHolder(view)
            }
        }
    }

    class ObjectiveComparator : DiffUtil.ItemCallback<Objective>() {
        override fun areItemsTheSame(oldItem: Objective, newItem: Objective): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Objective, newItem: Objective): Boolean {
            return oldItem.name == newItem.name
        }
    }
}