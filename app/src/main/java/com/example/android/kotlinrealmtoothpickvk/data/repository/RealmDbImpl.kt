package com.example.android.kotlinrealmtoothpickvk.data.repository

import io.reactivex.Flowable
import io.realm.Realm
import io.realm.kotlin.createObject

class RealmDbImpl : IRealmDb {

    override fun getAll(realm: Realm): Flowable<List<ModelGroup>> {
        //   realm = Realm.getInstance(realmConfig)
        realm.beginTransaction()
        val result = realm.where(ModelGroup::class.java).findAll()
        val models: List<ModelGroup> = realm.copyFromRealm(realm.where(ModelGroup::class.java).findAll())
        realm.commitTransaction()
        //    realm.close()
        return result.asFlowable()
       // return Flowable.just(models)
    }

    override fun getFavorite(realm: Realm): Flowable<List<ModelGroup>> {
        // realm = Realm.getInstance(realmConfig)
        realm.beginTransaction()
        val models: List<ModelGroup> =
            realm.copyFromRealm(realm.where(ModelGroup::class.java).equalTo("isFavorite", true).findAll())
        realm.commitTransaction()
        //    realm.close()
        return Flowable.just(models)
    }

    override fun deleteAll(realm: Realm) {
        //  realm = Realm.getInstance(realmConfig)
        //  realm.clear(ModelGroup::class.java)
        //  Realm.deleteRealm()
        //  realm.close()
        realm.beginTransaction()
        realm.where(ModelGroup::class.java).findAll()
        realm.deleteAll()
        realm.commitTransaction()
    }

    override fun insertModel(model: ModelGroup, realm: Realm) {
        //    realm = Realm.getInstance(realmConfig)
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(model)
        realm.commitTransaction()
        //   realm.close()
        //    Realm.compactRealm(realmConfig)
    }

    override fun insertModels(listModels: List<ModelGroup>, realm: Realm) {
      /*  realm.executeTransaction{
            val model: ModelGroup = realm.createObject(ModelGroup::class.java)
            for (i in listModels){
              //  model.id = i.id
                model.name = i.name
                model.subscribers = i.subscribers
                model.avatar = i.avatar
                model.isFavorite = i.isFavorite
            }
        }*/
        //   realm = Realm.getInstance(realmConfig)
        realm.beginTransaction()
      //  realm.insert(listModels)
           realm.copyToRealm(listModels)
        realm.commitTransaction()
        // realm.close()
        //     Realm.compactRealm(realmConfig)
    }
}