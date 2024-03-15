package com.example.a40kcc.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Team

class TeamListAdapter :
    ListAdapter<Team, TeamListAdapter.TeamViewHolder>(TeamComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            teamItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): TeamViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.data_object, parent, false)
                return TeamViewHolder(view)
            }
        }
    }

    class TeamComparator : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.name == newItem.name
        }
    }
}