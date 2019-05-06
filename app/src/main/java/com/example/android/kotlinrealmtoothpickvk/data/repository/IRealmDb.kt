package com.example.android.kotlinrealmtoothpickvk.data.repository

import io.reactivex.Flowable
import io.realm.Realm

interface IRealmDb {
    fun getAll(realm: Realm): Flowable<List<ModelGroup>>

    fun getFavorite(realm: Realm): Flowable<List<ModelGroup>>

    fun insertModels(listModels: List<ModelGroup>, realm: Realm)

    fun updateFavorite(model: ModelGroup, realm: Realm)

    fun getAllList(realm: Realm): List<ModelGroup>

    fun insert(model: ModelGroup, realm: Realm)

    fun delete(model: ModelGroup, realm: Realm)
}