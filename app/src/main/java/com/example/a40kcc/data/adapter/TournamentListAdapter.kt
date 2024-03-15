package com.example.a40kcc.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Tournament

class TournamentListAdapter :
    ListAdapter<Tournament, TournamentListAdapter.TournamentViewHolder>(TournamentComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentViewHolder {
        return TournamentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class TournamentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tournamentItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            tournamentItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): TournamentViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.data_object, parent, false)
                return TournamentViewHolder(view)
            }
        }
    }

    class TournamentComparator : DiffUtil.ItemCallback<Tournament>() {
        override fun areItemsTheSame(oldItem: Tournament, newItem: Tournament): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Tournament, newItem: Tournament): Boolean {
            return oldItem.name == newItem.name
        }
    }
}