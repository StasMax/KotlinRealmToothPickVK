package com.example.android.kotlinrealmtoothpickvk.data.repository

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.Action
import io.realm.Realm

class LocalDbRepositoryImpl : ILocalDbRepository {

    private var realmDb: IRealmDb = RealmDbImpl()
    private var listDb = arrayListOf<ModelGroup>()
    private var listVk = arrayListOf<ModelGroup>()

    override fun insertAll(vkModels: List<ModelGroup>, realm: Realm): Completable {
        return Completable.fromAction(Action {

            Log.e("QQQ", vkModels.size.toString())
            listVk.clear()
            listDb.clear()
            listVk.addAll(vkModels)
            listDb.addAll(realmDb.getAllList(realm))

            for (groupModel in listVk) {
                if (!listDb.contains(groupModel)) {
                    realmDb.insert(groupModel, realm)
                }
            }
            for (groupModel in listDb) {
                if (!listVk.contains(groupModel)) {
                    realmDb.delete(groupModel, realm)
                }
            }
        })
    }

    override fun update(model: ModelGroup, realm: Realm): Completable {
        return Completable.fromAction(Action { realmDb.updateFavorite(model, realm) })
    }

    override fun getAllFromLocalDb(realm: Realm): Flowable<List<ModelGroup>> {
        return realmDb.getAll(realm)
    }

    override fun getFavoriteFromLocalDb(realm: Realm): Flowable<List<ModelGroup>> {
        return realmDb.getFavorite(realm)
    }
}