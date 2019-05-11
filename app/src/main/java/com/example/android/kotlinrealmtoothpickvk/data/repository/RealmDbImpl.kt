package com.example.android.kotlinrealmtoothpickvk.data.repository

import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.data.repository.decorator.EmptyQueryDecorator
import com.example.android.kotlinrealmtoothpickvk.data.repository.decorator.QueryDecorator
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmObject
import javax.inject.Inject

class RealmDbImpl
@Inject constructor() : IRealmDb {

    override fun <T : RealmObject> saveItem(item: T) = saveItems(listOf(item))

    override fun <T : RealmObject> saveItems(list: List<T>?) {
        if (list.isNullOrEmpty()) return
        val realm = Realm.getDefaultInstance()
        realm.use {
            it.executeTransaction { transaction ->
                transaction.copyToRealmOrUpdate(list)
            }
        }
        realm.close()
    }

    override fun updateFavorite(decorator: QueryDecorator, model: ModelGroup) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            val result = (decorator.decorateQuery(it.where(ModelGroup::class.java)).findFirst())
            result!!.isFavorite = model.isFavorite
        }
        realm.close()
    }

    override fun <E : RealmObject> delItem(clazz: Class<E>) = delItem(EmptyQueryDecorator(), clazz)

    override fun <E : RealmObject> delItem(decorator: QueryDecorator, clazz: Class<E>) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            (decorator.decorateQuery(realm.where(clazz)).findFirst()?.deleteFromRealm())
        }
        realm.close()
    }

    override fun <E : RealmObject> getItem(decorator: QueryDecorator, clazz: Class<E>): E? {
        val realm = Realm.getDefaultInstance()
        return realm.use {
            val items = decorator.decorateQuery(realm.where(clazz)).findAll()
            if (items.size == 1) realm.copyFromRealm(items)[0]
            else null
        }
    }

    override fun <E : RealmObject> getItems(clazz: Class<E>) = getItems(EmptyQueryDecorator(), clazz)

    override fun <E : RealmObject> getItems(decorator: QueryDecorator, clazz: Class<E>) =
        Flowable.just(getItemList(decorator, clazz))

    override fun <E : RealmObject> getItemList(decorator: QueryDecorator, clazz: Class<E>): List<E> {
        val realm = Realm.getDefaultInstance()
        return realm.use {
            val realmList = decorator.decorateQuery(realm.where(clazz)).findAll()
            realm.copyFromRealm(realmList)
        }
    }
}