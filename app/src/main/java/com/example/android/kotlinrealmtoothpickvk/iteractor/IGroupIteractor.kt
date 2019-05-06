package com.example.android.kotlinrealmtoothpickvk.iteractor

import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.Realm

interface IGroupIteractor {
    fun getAllGroups(realm: Realm): Flowable<List<ModelGroup>>

    fun getFavoriteGroups(realm: Realm): Flowable<List<ModelGroup>>

    fun putModelsInDb(models: List<ModelGroup>, realm: Realm): Completable

    fun updeteModelInDb(model: ModelGroup, realm: Realm): Completable

    fun getAllListGroupsVk(): Single<List<ModelGroup>>

}