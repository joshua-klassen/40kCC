package com.example.a40kcc.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Round

class RoundListAdapter :
    ListAdapter<Round, RoundListAdapter.RoundViewHolder>(RoundComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundViewHolder {
        return RoundViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RoundViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.roundID.toString())
    }

    class RoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val roundItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            roundItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): RoundViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.data_object, parent, false)
                return RoundViewHolder(view)
            }
        }
    }

    class RoundComparator : DiffUtil.ItemCallback<Round>() {
        override fun areItemsTheSame(oldItem: Round, newItem: Round): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Round, newItem: Round): Boolean {
            return oldItem.roundID == newItem.roundID
        }
    }
}