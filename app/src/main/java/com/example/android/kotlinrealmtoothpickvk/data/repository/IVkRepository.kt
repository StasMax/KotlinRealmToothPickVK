package com.example.android.kotlinrealmtoothpickvk.data.repository

import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import io.reactivex.Single

interface IVkRepository {
    fun getGroupsFromVk(): Single<List<ModelGroup>>
}