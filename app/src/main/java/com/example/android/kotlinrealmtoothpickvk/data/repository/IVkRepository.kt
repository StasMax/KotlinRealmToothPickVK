package com.example.android.kotlinrealmtoothpickvk.data.repository

import io.reactivex.Single

interface IVkRepository {
    fun getGroupsFromVk(): Single<List<ModelGroup>>
}