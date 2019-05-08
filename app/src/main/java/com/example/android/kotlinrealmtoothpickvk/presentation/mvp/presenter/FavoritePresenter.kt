package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.iteractor.IGroupIteractor
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view.FavoriteView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class FavoritePresenter @Inject constructor(var iteractor: IGroupIteractor) : BasePresenter<FavoriteView>() {

    fun onInitFavoriteGroups() {
        disposeBag(iteractor.getFavoriteGroups()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onInitGroupsRecycle(it) })
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
        disposeBag(
            iteractor.updeteModelInDb(groupModel)
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }
}