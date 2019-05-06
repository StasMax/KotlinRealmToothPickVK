package com.example.android.kotlinrealmtoothpickvk.presentation.app

import android.app.Application
import android.content.Context
import com.vk.sdk.VKSdk
import io.realm.Realm
import io.realm.RealmConfiguration


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
        Realm.init(this)
        val c = RealmConfiguration.Builder()
        c.name("modelGroup")
        c.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(c.build())


       //     realm = Realm.getDefaultInstance()
        /*   Toothpick.setConfiguration(Configuration.forProduction())

        val appScope: Scope = Toothpick.openScope(this)
        initToothpick(appScope)
    }

    fun initToothpick(appScope: Scope) {
        appScope.installModules(SmoothieApplicationModule(this))
    }*/
    }
  /*  companion object ObjectApp {
        lateinit var config: RealmConfiguration

    }*/
}