package com.example.android.kotlinrealmtoothpickvk.data.repository.decorator

import io.realm.RealmModel
import io.realm.RealmQuery

open class EmptyQueryDecorator : QueryDecorator {
    override fun <T : RealmModel> decorateQuery(query: RealmQuery<T>): RealmQuery<T> = query
}