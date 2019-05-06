package com.example.android.kotlinrealmtoothpickvk.data.repository

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.Action
import io.realm.Realm

class LocalDbRepositoryImpl : ILocalDbRepository {

    private var vkRepository: IVkRepository = VkRepositoryImpl()
    private var realmDb: IRealmDb = RealmDbImpl()

    override fun insertAll(realm: Realm): Completable {
        return Completable.fromAction(Action {
            realmDb.deleteAll(realm)
            val listModelGroup = vkRepository.getGroupsFromVk()
            Log.e("EEE", listModelGroup.size.toString())
            realmDb.insertModels(listModelGroup, realm)
        })
    }

    override fun update(
        model: ModelGroup,
        realm: Realm
    ): Completable {
        return Completable.fromAction(Action {
            realmDb.insertModel(model, realm)
        })
    }

    override fun getAllFromLocalDb(realm: Realm): Flowable<List<ModelGroup>> {
        return realmDb.getAll(realm)
    }

    override fun getFavoriteFromLocalDb(realm: Realm): Flowable<List<ModelGroup>> {
        return realmDb.getFavorite(realm)
    }
}