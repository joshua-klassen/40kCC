package com.example.a40kcc.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Faction

class FactionListAdapter :
    ListAdapter<Faction, FactionListAdapter.FactionViewHolder>(FactionComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactionViewHolder {
        return FactionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FactionViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class FactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val factionItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            factionItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): FactionViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.data_object, parent, false)
                return FactionViewHolder(view)
            }
        }
    }

    class FactionComparator : DiffUtil.ItemCallback<Faction>() {
        override fun areItemsTheSame(oldItem: Faction, newItem: Faction): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Faction, newItem: Faction): Boolean {
            return oldItem.name == newItem.name
        }
    }
}