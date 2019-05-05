package com.example.android.kotlinrealmtoothpickvk.iteractor

import com.example.android.kotlinrealmtoothpickvk.data.repository.ILocalDbRepository
import com.example.android.kotlinrealmtoothpickvk.data.repository.LocalDbRepositoryImpl
import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
import io.reactivex.Completable
import io.reactivex.Flowable
import io.realm.Realm

class GroupIteractorImpl() : IGroupIteractor {

    private var localRepository: ILocalDbRepository = LocalDbRepositoryImpl()

    override fun putModelsInDb(realm: Realm): Completable {
        return localRepository.insertAll(realm)
    }

    override fun updeteModelInDb(model: ModelGroup, realm: Realm): Completable {
        return localRepository.update(model, realm)
    }

    override fun getFavoriteGroups(realm: Realm): Flowable<List<ModelGroup>> {
        return localRepository.getFavoriteFromLocalDb(realm)
    }

    override fun getAllGroups(realm: Realm): Flowable<List<ModelGroup>> {
        return localRepository.getAllFromLocalDb(realm)
    }
}