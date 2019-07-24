package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.presenter

import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import com.example.android.kotlinrealmtoothpickvk.R
import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.iteractor.IGroupInteractor
import com.example.android.kotlinrealmtoothpickvk.presentation.asyncCompletable
import com.example.android.kotlinrealmtoothpickvk.presentation.asyncFlowable
import com.example.android.kotlinrealmtoothpickvk.presentation.asyncSingle
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view.GroupView
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import javax.inject.Inject

@InjectViewState
class GroupPresenter
@Inject constructor(
    private val interactor: IGroupInteractor
) : BasePresenter<GroupView>() {

    fun loginVk(requestCode: Int, resultCode: Int, data: Intent?) {
        VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
            override fun onResult(res: VKAccessToken) {
                onInitGroupsVk()
            }

            override fun onError(error: VKError) {
                viewState.showError(R.string.error_login)
            }
        })
    }

    fun onInitGroupsVk() {
        interactor
            .getAllListGroupsVk()
            .asyncSingle()
            .doOnSubscribe { viewState.startLoading() }
            .flatMapCompletable { interactor.putModelsInDb(it) }
            .subscribe()
            .let { disposeBag(it) }
    }

    fun onInitGroupsDb() {
        interactor
            .getAllGroups()
            .asyncFlowable()
            .subscribe {
                onInitGroupsRecycle(it)
                viewState.endLoading()
            }
            .let { disposeBag(it) }
    }


}