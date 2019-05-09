package com.example.android.kotlinrealmtoothpickvk.presentation.app

import android.app.Application
import com.github.stephanenicolas.toothpick.smoothie.BuildConfig
import com.vk.sdk.VKSdk
import io.realm.Realm
import io.realm.RealmConfiguration
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
        initRealm()
        initToothpick()
    }

    private fun initRealm() {
        Realm.init(this)
        val c = RealmConfiguration.Builder()
        c.name("modelGroup")
        c.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(c.build())
    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
            MemberInjectorRegistryLocator.setRootRegistry(com.example.android.kotlinrealmtoothpickvk.MemberInjectorRegistry())
            FactoryRegistryLocator.setRootRegistry(com.example.android.kotlinrealmtoothpickvk.FactoryRegistry())
        }
    }
}


