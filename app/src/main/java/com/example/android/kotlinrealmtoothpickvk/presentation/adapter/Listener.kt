package com.example.android.kotlinrealmtoothpickvk.presentation.adapter

import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup

interface Listener {
    fun onClick(groupModel: ModelGroup, isChecked: Boolean)
}