package com.example.btikotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SearchAdapter: ListAdapter<Food, SearchAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Food>(){
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }
    }

    lateinit var onItemClickListener: (Food) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_list_item,
            parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        val food = getItem(position)
        holder.bind(food)
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        private val nameTV = view.findViewById<TextView>(R.id.nameTV)

        fun bind(food: Food){
            nameTV.text = food.name

            view.setOnClickListener {
                if(::onItemClickListener.isInitialized){
                    onItemClickListener(food)
                }
            }
        }
    }
}