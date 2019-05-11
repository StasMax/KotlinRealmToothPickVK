package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.iteractor.IGroupInteractor
import com.example.android.kotlinrealmtoothpickvk.presentation.asyncCompletable
import com.example.android.kotlinrealmtoothpickvk.presentation.asyncFlowable
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view.FavoriteView
import javax.inject.Inject

@InjectViewState
class FavoritePresenter
@Inject constructor(
    private val interactor: IGroupInteractor
) : BasePresenter<FavoriteView>() {

    fun onInitFavoriteGroups() {
        interactor
            .getFavoriteGroups()
            .asyncFlowable()
            .subscribe { onInitGroupsRecycle(it) }
            .let { disposeBag(it) }
    }

    private fun onInitGroupsRecycle(groupModelList: List<ModelGroup>) {
        if (groupModelList.isEmpty()) {
            viewState.setupEmptyList()
        } else {
            viewState.setupGroupsList(groupModelList)
        }
    }

    fun onSetFavorite(groupModel: ModelGroup, isChecked: Boolean) {
        groupModel.isFavorite = isChecked
        interactor
            .updeteModelInDb(groupModel)
            .asyncCompletable()
            .subscribe()
            .let { disposeBag(it) }
    }
}