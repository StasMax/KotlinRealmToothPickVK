package com.example.android.kotlinrealmtoothpickvk.iteractor

import com.example.android.kotlinrealmtoothpickvk.data.repository.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.Realm

class GroupIteractorImpl() : IGroupIteractor {

    private var localRepository: ILocalDbRepository = LocalDbRepositoryImpl()
    private var vkRepository: IVkRepository = VkRepositoryImpl()

    override fun putModelsInDb(models: List<ModelGroup>, realm: Realm): Completable {
        return localRepository.insertAll(models, realm)
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

    override fun getAllListGroupsVk(): Single<List<ModelGroup>> {
       return vkRepository.getGroupsFromVk()
    }
}