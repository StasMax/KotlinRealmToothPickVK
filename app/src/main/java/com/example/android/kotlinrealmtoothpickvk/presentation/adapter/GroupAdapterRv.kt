package com.example.android.kotlinrealmtoothpickvk.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import com.example.android.kotlinrealmtoothpickvk.R
import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
import java.util.ArrayList

class GroupAdapterRv : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val groupsModelList = ArrayList<ModelGroup>()
    private val sourceList = ArrayList<ModelGroup>()
    lateinit var listener: Listener

    fun setupGroups(groupModelList: List<ModelGroup>) {
        groupsModelList.clear()
        sourceList.clear()
        sourceList.addAll(groupModelList)
        filter("")
    }

    fun setupFavoriteGroups(groupModelListFavorite: List<ModelGroup>) {
        groupsModelList.clear()
        groupsModelList.addAll(groupModelListFavorite)
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        groupsModelList.clear()
        sourceList.forEach {
            if (it.name?.contains(query, ignoreCase = false)!!) {
                groupsModelList.add(it)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderGroup {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val itemView = layoutInflater.inflate(R.layout.recycler, viewGroup, false)
        return ViewHolderGroup(itemView = itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        if (viewHolder is ViewHolderGroup) {
            viewHolder.bind(groupModel = groupsModelList[i])
            val checkBox: CheckBox = viewHolder.itemView.findViewById(R.id.isFavorite_checkBox)
            checkBox.setOnCheckedChangeListener { view, isChecked ->
                listener.onClick(groupsModelList[i], isChecked)
            }
        }
    }

    override fun getItemCount(): Int {
        return groupsModelList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}