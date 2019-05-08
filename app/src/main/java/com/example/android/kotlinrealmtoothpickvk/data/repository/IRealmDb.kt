package com.example.android.kotlinrealmtoothpickvk.data.repository

import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import io.reactivex.Flowable

interface IRealmDb {
    fun getAll(): Flowable<List<ModelGroup>>

    fun getFavorite(): Flowable<List<ModelGroup>>

    fun insertModels(listModels: List<ModelGroup>)

    fun updateFavorite(model: ModelGroup)

    fun getAllList(): List<ModelGroup>

    fun insert(model: ModelGroup)

    fun delete(model: ModelGroup)
}