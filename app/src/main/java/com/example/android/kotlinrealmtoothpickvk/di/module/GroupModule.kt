package com.example.android.kotlinrealmtoothpickvk.di.module

import com.example.android.kotlinrealmtoothpickvk.data.repository.*
import com.example.android.kotlinrealmtoothpickvk.iteractor.GroupInteractorImpl
import com.example.android.kotlinrealmtoothpickvk.iteractor.IGroupInteractor
import toothpick.config.Module

class GroupModule : Module() {
    init {
        this.bind(IVkRepository::class.java).to(VkRepositoryImpl::class.java).singletonInScope()
        this.bind(IRealmDb::class.java).to(RealmDbImpl::class.java).singletonInScope()
        this.bind(ILocalDbRepository::class.java).to(LocalDbRepositoryImpl::class.java).singletonInScope()
        this.bind(IGroupInteractor::class.java).to(GroupInteractorImpl::class.java).singletonInScope()
    }
}