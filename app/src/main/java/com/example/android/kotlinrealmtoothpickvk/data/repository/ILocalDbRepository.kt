package com.example.android.kotlinrealmtoothpickvk.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.realm.Realm

interface ILocalDbRepository {
    fun insertAll(realm: Realm): Completable

    fun update(model: ModelGroup, realm: Realm): Completable

    fun getAllFromLocalDb(realm: Realm): Flowable<List<ModelGroup>>

    fun getFavoriteFromLocalDb(realm: Realm): Flowable<List<ModelGroup>>
}