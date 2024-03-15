package com.example.a40kcc.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Player

class PlayerListAdapter :
    ListAdapter<Player, PlayerListAdapter.PlayerViewHolder>(PlayerComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            playerItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): PlayerViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.data_object, parent, false)
                return PlayerViewHolder(view)
            }
        }
    }

    class PlayerComparator : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.name == newItem.name
        }
    }
}