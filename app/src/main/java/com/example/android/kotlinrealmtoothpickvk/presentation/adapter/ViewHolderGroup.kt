package com.example.android.kotlinrealmtoothpickvk.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.android.kotlinrealmtoothpickvk.R
import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.recycler.view.*

class ViewHolderGroup(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var civAvatar: CircleImageView = itemView.findViewById(R.id.groups_siv_avatar)

    fun bind(groupModel: ModelGroup) {
        itemView.group_txt_name.text = groupModel.name
        itemView.group_txt_subscribers.text = groupModel.subscribers
        itemView.isFavorite_checkBox.isChecked = groupModel.isFavorite
        groupModel.avatar?.let { Picasso.with(itemView.context).load(it).into(civAvatar) }
    }
}