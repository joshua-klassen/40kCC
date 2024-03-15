package com.example.a40kcc.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Outcome

class OutcomeListAdapter :
    ListAdapter<Outcome, OutcomeListAdapter.OutcomeViewHolder>(OutcomeComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutcomeViewHolder {
        return OutcomeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: OutcomeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.outcomeID.toString())
    }

    class OutcomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val outcomeItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            outcomeItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): OutcomeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.data_object, parent, false)
                return OutcomeViewHolder(view)
            }
        }
    }

    class OutcomeComparator : DiffUtil.ItemCallback<Outcome>() {
        override fun areItemsTheSame(oldItem: Outcome, newItem: Outcome): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Outcome, newItem: Outcome): Boolean {
            return oldItem.outcomeID == newItem.outcomeID
        }
    }
}