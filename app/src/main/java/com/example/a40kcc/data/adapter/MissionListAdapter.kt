package com.example.a40kcc.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Mission

class MissionListAdapter :
    ListAdapter<Mission, MissionListAdapter.MissionViewHolder>(MissionComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionViewHolder {
        return MissionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MissionViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class MissionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val missionItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            missionItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): MissionViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.data_object, parent, false)
                return MissionViewHolder(view)
            }
        }
    }

    class MissionComparator : DiffUtil.ItemCallback<Mission>() {
        override fun areItemsTheSame(oldItem: Mission, newItem: Mission): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Mission, newItem: Mission): Boolean {
            return oldItem.name == newItem.name
        }
    }
}