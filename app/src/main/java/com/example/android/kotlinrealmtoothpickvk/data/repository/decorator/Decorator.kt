package com.example.android.kotlinrealmtoothpickvk.data.repository.decorator

import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import io.realm.RealmModel
import io.realm.RealmQuery

fun getQueryDecoratorByFavorite() =
        object : QueryDecorator{
            override fun <T : RealmModel> decorateQuery(query: RealmQuery<T>): RealmQuery<T> {
              return query.equalTo("isFavorite", true)
            }
        }

fun getQueryDecoratorByName(item: ModelGroup) =
    object : QueryDecorator {
        override fun <T : RealmModel> decorateQuery(query: RealmQuery<T>): RealmQuery<T> {
            return query.equalTo("name", item.name)
        }
    }
