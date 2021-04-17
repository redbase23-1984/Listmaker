package com.raywenderlich.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter (val lists: ArrayList<TaskList>,
    val clickListener: ListSelectionRecyclerViewClickListener) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: TaskList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_selection_view_holder,
                parent,
                false)
        return  ListSelectionViewHolder(view)

    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.apply{
            listPosition.text = (position+1).toString()
            listTitle.text = lists.get(position).name
            itemView.setOnClickListener{
                clickListener.listItemClicked(lists[position])
            }
        }

        holder.listPosition.text  = (position+1).toString()
        holder.listTitle.text = lists.get(position).name
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun addList (list: TaskList){
        lists.add(list)
        notifyItemInserted(lists.size -1)
    }
}