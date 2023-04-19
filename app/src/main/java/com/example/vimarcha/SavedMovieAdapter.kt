package com.example.vimarcha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vimarcha.modeldata.MovieDataSaved

class SavedMovieAdapter(private var listItem: MutableList<String>) :RecyclerView.Adapter<SavedMovieAdapter.ViewHolder>(){
    private var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    fun removeItem(position: Int) {
        listItem.removeAt(position)
        notifyItemRemoved(position)

    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(title: String) {
            itemView.findViewById<TextView>(android.R.id.text1).text = title
            itemView.setOnClickListener {
                removeItem(adapterPosition)
            }
        }
    }
}