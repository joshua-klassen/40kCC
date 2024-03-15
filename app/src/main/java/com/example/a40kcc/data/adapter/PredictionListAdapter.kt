package com.example.a40kcc.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Prediction

class PredictionListAdapter :
    ListAdapter<Prediction, PredictionListAdapter.PredictionViewHolder>(PredictionComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredictionViewHolder {
        return PredictionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PredictionViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class PredictionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val predictionItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            predictionItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): PredictionViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.data_object, parent, false)
                return PredictionViewHolder(view)
            }
        }
    }

    class PredictionComparator : DiffUtil.ItemCallback<Prediction>() {
        override fun areItemsTheSame(oldItem: Prediction, newItem: Prediction): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Prediction, newItem: Prediction): Boolean {
            return oldItem.name == newItem.name
        }
    }
}