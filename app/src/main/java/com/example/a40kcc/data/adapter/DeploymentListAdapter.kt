package com.example.a40kcc.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Deployment

class DeploymentListAdapter :
    ListAdapter<Deployment, DeploymentListAdapter.DeploymentViewHolder>(DeploymentComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeploymentViewHolder {
        return DeploymentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DeploymentViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class DeploymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val deploymentItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            deploymentItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): DeploymentViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.data_object, parent, false)
                return DeploymentViewHolder(view)
            }
        }
    }

    class DeploymentComparator : DiffUtil.ItemCallback<Deployment>() {
        override fun areItemsTheSame(oldItem: Deployment, newItem: Deployment): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Deployment, newItem: Deployment): Boolean {
            return oldItem.name == newItem.name
        }
    }
}