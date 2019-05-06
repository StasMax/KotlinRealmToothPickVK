package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.presenter

import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import com.example.android.kotlinrealmtoothpickvk.R
import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.iteractor.GroupIteractorImpl
import com.example.android.kotlinrealmtoothpickvk.iteractor.IGroupIteractor
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view.GroupView
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import io.reactivex.internal.operators.flowable.FlowableFlatMap.subscribe
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

@InjectViewState
class GroupPresenter : BasePresenter<GroupView>() {

    var iteractor: IGroupIteractor = GroupIteractorImpl()
    private var realm: Realm = Realm.getDefaultInstance()

    fun loginVk(requestCode: Int, resultCode: Int, data: Intent?) {
        VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
            override fun onResult(res: VKAccessToken) {
                onInitGroupsVk()
                onInitGroupsDb()
            }

            override fun onError(error: VKError) {
                viewState.showError(R.string.error_login)
            }
        })
    }

    fun onInitGroupsVk(){
        disposeBag(iteractor.getAllListGroupsVk()
            .doOnSubscribe { viewState.startLoading() }
            .flatMapCompletable { iteractor.putModelsInDb(it, realm) }

        .subscribe())
    }

    fun onInitGroupsDb() {
        disposeBag(iteractor.getAllGroups(realm)
           // .subscribeOn(Schedulers.io())
          //  .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onInitGroupsRecycle(it)
            viewState.endLoading()})
    }

    private fun onInitGroupsRecycle(groupModelList: List<ModelGroup>) {
        if (groupModelList.isEmpty()) {
            viewState.setupEmptyList()
            viewState.showError(R.string.no_groups_item)
        } else {
            viewState.setupGroupsList(groupsList = groupModelList)
        }
    }

    fun onSetFavorite(groupModel: ModelGroup, isChecked: Boolean) {
        groupModel.isFavorite = isChecked
        disposeBag(
            iteractor.updeteModelInDb(groupModel, realm)
              //  .subscribeOn(Schedulers.newThread())
                .subscribe()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}