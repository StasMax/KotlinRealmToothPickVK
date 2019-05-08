package com.example.android.kotlinrealmtoothpickvk.data.repository

import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import io.reactivex.Completable
import io.reactivex.Flowable

interface ILocalDbRepository {
    fun insertAll(vkModels: List<ModelGroup>): Completable

    fun update(model: ModelGroup): Completable

    fun getAllFromLocalDb(): Flowable<List<ModelGroup>>

    fun getFavoriteFromLocalDb(): Flowable<List<ModelGroup>>
}