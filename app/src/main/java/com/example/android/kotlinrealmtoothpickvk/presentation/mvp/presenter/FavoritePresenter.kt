package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.iteractor.GroupIteractorImpl
import com.example.android.kotlinrealmtoothpickvk.iteractor.IGroupIteractor
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view.FavoriteView
import io.realm.Realm

@InjectViewState
class FavoritePresenter : BasePresenter<FavoriteView>() {
    var iteractor: IGroupIteractor = GroupIteractorImpl()
    private var realmF: Realm = Realm.getDefaultInstance()

    fun onInitFavoriteGroups() {
        disposeBag(iteractor.getFavoriteGroups(realmF)
            // .subscribeOn(Schedulers.io())
            //  .observeOn(AndroidSchedulers.mainThread())
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
        disposeBag(iteractor.updeteModelInDb(groupModel, realmF)
                //  .subscribeOn(Schedulers.newThread())
                .subscribe()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        realmF.close()
    }
}