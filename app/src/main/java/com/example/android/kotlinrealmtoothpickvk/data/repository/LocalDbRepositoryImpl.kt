package com.example.android.kotlinrealmtoothpickvk.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class LocalDbRepositoryImpl @Inject constructor(var realmDb: IRealmDb) : ILocalDbRepository {
    private var listDb = arrayListOf<ModelGroup>()
    private var listVk = arrayListOf<ModelGroup>()

    override fun insertAll(vkModels: List<ModelGroup>): Completable {
        return Completable.fromAction {
            listVk.clear()
            listDb.clear()
            listVk.addAll(vkModels)
            listDb.addAll(realmDb.getAllList())

            for (groupModel in listVk) {
                if (!listDb.contains(groupModel)) {
                    realmDb.insert(groupModel)
                }
            }
            for (groupModel in listDb) {
                if (!listVk.contains(groupModel)) {
                    realmDb.delete(groupModel)
                }
            }
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