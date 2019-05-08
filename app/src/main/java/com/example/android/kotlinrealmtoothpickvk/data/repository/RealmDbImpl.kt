package com.example.android.kotlinrealmtoothpickvk.data.repository

import io.reactivex.Flowable
import io.realm.Realm
import javax.inject.Inject

class RealmDbImpl @Inject constructor() : IRealmDb {

    lateinit var realmN: Realm

    override fun getAll(): Flowable<List<ModelGroup>> {
        realmN = Realm.getDefaultInstance()
        val models: List<ModelGroup> = realmN.copyFromRealm(realmN.where(ModelGroup::class.java).findAllAsync())
        realmN.close()
        return Flowable.just(models)
    }

    override fun getAllList(): List<ModelGroup> {
        realmN = Realm.getDefaultInstance()
        val models: List<ModelGroup> = realmN.copyFromRealm(realmN.where(ModelGroup::class.java).findAllAsync())
        realmN.close()
        return models
    }

    override fun getFavorite(): Flowable<List<ModelGroup>> {
        realmN = Realm.getDefaultInstance()
        val models: List<ModelGroup> =
            realmN.copyFromRealm(realmN.where(ModelGroup::class.java).equalTo("isFavorite", true).findAllAsync())
        realmN.close()
        return Flowable.just(models)
    }

    override fun delete(model: ModelGroup) {
        realmN = Realm.getDefaultInstance()
        realmN.executeTransactionAsync {
            val result = realmN.where(ModelGroup::class.java).equalTo("name", model.name).findFirst()
            result!!.deleteFromRealm()
        }
        realmN.close()
    }

    override fun updateFavorite(model: ModelGroup) {
        realmN = Realm.getDefaultInstance()
        realmN.executeTransactionAsync {
            val result = it.where(ModelGroup::class.java).equalTo("name", model.name).findFirst()
            result!!.isFavorite = model.isFavorite
        }
        realmN.close()
    }

    override fun insert(model: ModelGroup) {
        realmN = Realm.getDefaultInstance()
        realmN.executeTransactionAsync {
            it.copyToRealm(model)
        }
        realmN.close()
    }

    override fun insertModels(listModels: List<ModelGroup>) {
        realmN = Realm.getDefaultInstance()
        realmN.executeTransactionAsync {
            it.copyToRealm(listModels)
        }
        realmN.close()
    }
}