package com.example.android.kotlinrealmtoothpickvk.presentation.app

import android.app.Application
import android.content.Context
import com.vk.sdk.VKSdk
import io.realm.Realm
import io.realm.RealmConfiguration
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.smoothie.module.SmoothieApplicationModule

class App : Application() {



    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
        Realm.init(this)


        var c = RealmConfiguration.Builder()
        c.name("student")
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