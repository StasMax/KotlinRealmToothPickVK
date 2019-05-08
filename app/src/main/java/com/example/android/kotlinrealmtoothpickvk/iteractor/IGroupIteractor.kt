package com.example.android.kotlinrealmtoothpickvk.iteractor

import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface IGroupIteractor {
    fun getAllGroups(): Flowable<List<ModelGroup>>

    fun getFavoriteGroups(): Flowable<List<ModelGroup>>

    fun putModelsInDb(models: List<ModelGroup>): Completable

    fun updeteModelInDb(model: ModelGroup): Completable

    fun getAllListGroupsVk(): Single<List<ModelGroup>>
}