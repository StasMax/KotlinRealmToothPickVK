package com.example.android.kotlinrealmtoothpickvk.data.repository

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class ModelGroup (var name:String? = null, var subscribers:String? = null, var avatar:String? = null, var isFavorite:Boolean = false) :
    RealmObject() {
   // @PrimaryKey
  //  open var id: Int = 0
}