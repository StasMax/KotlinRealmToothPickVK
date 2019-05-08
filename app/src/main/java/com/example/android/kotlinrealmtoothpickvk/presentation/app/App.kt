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

        Realm.init(this)
        val c = RealmConfiguration.Builder()
        c.name("modelGroup")
        c.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(c.build())

        Toothpick.setConfiguration(
            if (BuildConfig.DEBUG) {
                Configuration.forDevelopment()
            } else {
                Configuration.forProduction().disableReflection()
            }
        )
        MemberInjectorRegistryLocator.setRootRegistry(com.example.android.kotlinrealmtoothpickvk.MemberInjectorRegistry())
        FactoryRegistryLocator.setRootRegistry(com.example.android.kotlinrealmtoothpickvk.FactoryRegistry())
    }
}


