package com.example.android.kotlinrealmtoothpickvk.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.example.android.kotlinrealmtoothpickvk.R
import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ViewHolderGroup(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var civAvatar = itemView.findViewById<CircleImageView>(R.id.groups_siv_avatar)
    var txtGroupName = itemView.findViewById<TextView>(R.id.group_txt_name)
    var txtSubscribers = itemView.findViewById<TextView>(R.id.group_txt_subscribers)
    var checkBox = itemView.findViewById<CheckBox>(R.id.isFavorite_checkBox)

    fun bind(groupModel: ModelGroup) {
        txtGroupName.text = groupModel.name
        txtSubscribers.text = groupModel.subscribers
        checkBox.isChecked = groupModel.isFavorite
        groupModel.avatar?.let { Picasso.with(itemView.context).load(it).into(civAvatar) }
    }
}