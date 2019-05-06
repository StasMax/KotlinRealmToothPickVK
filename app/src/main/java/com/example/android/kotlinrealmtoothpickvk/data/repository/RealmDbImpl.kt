package com.example.android.kotlinrealmtoothpickvk.data.repository

import android.util.Log
import io.reactivex.Flowable
import io.realm.Realm

class RealmDbImpl : IRealmDb {

    override fun getAll(realm: Realm): Flowable<List<ModelGroup>> {
        val models: List<ModelGroup> = realm.copyFromRealm(realm.where(ModelGroup::class.java).findAll())
        Log.e("EEE", models.size.toString())
        return Flowable.just(models)
    }

    override fun getAllList(realm: Realm): List<ModelGroup> {
        val models: List<ModelGroup> = realm.copyFromRealm(realm.where(ModelGroup::class.java).findAll())
        Log.e("EEE", models.size.toString())
        return models
    }

    override fun getFavorite(realm: Realm): Flowable<List<ModelGroup>> {
        val models: List<ModelGroup> =
            realm.copyFromRealm(realm.where(ModelGroup::class.java).equalTo("isFavorite", true).findAll())
        return Flowable.just(models)
    }

    override fun delete(model: ModelGroup, realm: Realm) {
        realm.executeTransaction {
            val result = realm.where(ModelGroup::class.java).equalTo("name", model.name).findFirst()
            result!!.deleteFromRealm()
        }
    }

    override fun updateFavorite(model: ModelGroup, realm: Realm) {
        realm.executeTransaction {
            val result = realm.where(ModelGroup::class.java).equalTo("name", model.name).findFirst()
            result!!.isFavorite = model.isFavorite
        }
    }

    override fun insert(model: ModelGroup, realm: Realm) {
        realm.beginTransaction()
        realm.copyToRealm(model)
        realm.commitTransaction()
    }

    override fun insertModels(listModels: List<ModelGroup>, realm: Realm) {
        realm.beginTransaction()
        realm.copyToRealm(listModels)
        realm.commitTransaction()
    }
}