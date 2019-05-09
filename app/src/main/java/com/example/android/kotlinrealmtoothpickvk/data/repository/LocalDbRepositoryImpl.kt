package com.example.android.kotlinrealmtoothpickvk.data.repository

import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class LocalDbRepositoryImpl
@Inject constructor(
    private val realmDb: IRealmDb
) : ILocalDbRepository {

    private var listDb = arrayListOf<ModelGroup>()
    private var listVk = arrayListOf<ModelGroup>()

    override fun insertAll(vkModels: List<ModelGroup>): Completable {
        return Completable.fromAction {
            listVk.clear()
            listDb.clear()
            listVk.addAll(vkModels)
            listDb.addAll(realmDb.getAllList())
            listVk.filter { !listDb.contains(it) }.forEach { realmDb::insert }
            listDb.filter { !listVk.contains(it) }.forEach { realmDb::delete }
        }
    }

    override fun update(model: ModelGroup): Completable {
        return Completable.fromAction { realmDb.updateFavorite(model) }
    }

    override fun getAllFromLocalDb(): Flowable<List<ModelGroup>> {
        return realmDb.getAll()
    }

    override fun getFavoriteFromLocalDb(): Flowable<List<ModelGroup>> {
        return realmDb.getFavorite()
    }
}