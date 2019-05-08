package com.example.android.kotlinrealmtoothpickvk.presentation.adapter

import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup

interface Listener {
    fun onClick(groupModel: ModelGroup, isChecked: Boolean)
}