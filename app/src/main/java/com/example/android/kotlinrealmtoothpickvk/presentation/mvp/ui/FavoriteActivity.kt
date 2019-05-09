package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.ui

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.android.kotlinrealmtoothpickvk.R
import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.di.module.GroupModule
import com.example.android.kotlinrealmtoothpickvk.presentation.adapter.GroupAdapterRv
import com.example.android.kotlinrealmtoothpickvk.presentation.adapter.Listener
import com.example.android.kotlinrealmtoothpickvk.presentation.makeUnvisible
import com.example.android.kotlinrealmtoothpickvk.presentation.makeVisible
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.presenter.FavoritePresenter
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view.FavoriteView
import kotlinx.android.synthetic.main.activity_favorite.*
import toothpick.Toothpick
import javax.inject.Inject

class FavoriteActivity : FavoriteView, BaseActivity() {
    @Inject
    @InjectPresenter
    lateinit var favoritePresenter: FavoritePresenter

    @ProvidePresenter
    fun providePresenter() = favoritePresenter

    lateinit var groupAdapterRv: GroupAdapterRv

    override fun onCreate(savedInstanceState: Bundle?) {
       val scope = Toothpick.openScope("favoriteScope")
        scope.installModules(GroupModule())
        Toothpick.inject(this, scope)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        favoritePresenter.onInitFavoriteGroups()
        groupAdapterRv = GroupAdapterRv()
        favoriteRecyclerView.adapter = groupAdapterRv
        favoriteListener(groupAdapterRv)
    }

    private fun favoriteListener(groupAdapterRv: GroupAdapterRv) {
        groupAdapterRv.listener = (object : Listener {
            override fun onClick(groupModel: ModelGroup, isChecked: Boolean) {
                favoritePresenter.onSetFavorite(groupModel, isChecked)
            }
        })
    }

    override fun setupEmptyList() {
        txtGroupsNoItemFavorite.makeVisible()
    }

    override fun setupGroupsList(groupModelFavoriteList: List<ModelGroup>) {
        txtGroupsNoItemFavorite.makeUnvisible()
        groupAdapterRv.setupFavoriteGroups(groupModelFavoriteList)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope("favoriteScope")
    }
}
