package com.example.android.kotlinrealmtoothpickvk.data.repository

import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.data.repository.decorator.QueryDecorator
import io.reactivex.Flowable
import io.realm.RealmObject

interface IRealmDb {

    fun <T : RealmObject> saveItem(item: T)

    fun <T : RealmObject> saveItems(list: List<T>?)

    fun <E : RealmObject> getItem(decorator: QueryDecorator, clazz: Class<E>): E?

    fun <E : RealmObject> delItem(clazz: Class<E>)

    fun <E : RealmObject> delItem(decorator: QueryDecorator, clazz: Class<E>)

    fun <E : RealmObject> getItems(clazz: Class<E>): Flowable<List<E>>

    fun <E : RealmObject> getItems(decorator: QueryDecorator, clazz: Class<E>): Flowable<List<E>>

    fun <E : RealmObject> getItemList(decorator: QueryDecorator, clazz: Class<E>): List<E>

    fun updateFavorite(decorator: QueryDecorator, model: ModelGroup)
}