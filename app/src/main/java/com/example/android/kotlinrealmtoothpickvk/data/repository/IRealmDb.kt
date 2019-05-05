package com.example.android.kotlinrealmtoothpickvk.data.repository

import io.reactivex.Flowable
import io.realm.Realm

interface IRealmDb {
    fun getAll(realm: Realm): Flowable<List<ModelGroup>>

    fun getFavorite(realm: Realm): Flowable<List<ModelGroup>>

    fun deleteAll(realm: Realm)

    fun insertModels(
        listModels: List<ModelGroup>,
        realm: Realm
    )

    fun insertModel(model: ModelGroup, realm: Realm)
}