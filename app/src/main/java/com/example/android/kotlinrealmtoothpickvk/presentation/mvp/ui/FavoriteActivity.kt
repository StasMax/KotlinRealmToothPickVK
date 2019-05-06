package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.android.kotlinrealmtoothpickvk.R
import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.presentation.adapter.GroupAdapterRv
import com.example.android.kotlinrealmtoothpickvk.presentation.adapter.Listener
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.presenter.FavoritePresenter
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view.FavoriteView
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : FavoriteView, BaseActivity() {

    @InjectPresenter
    lateinit var favoritePresenter: FavoritePresenter

    lateinit var groupAdapterRv: GroupAdapterRv

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        favoritePresenter.onInitFavoriteGroups()
        groupAdapterRv = GroupAdapterRv()
        favorite_recycler_view.adapter = groupAdapterRv
        favorite_recycler_view.layoutManager = LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
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
        txt_groups_no_item_favorite.visibility = View.VISIBLE
    }

    override fun setupGroupsList(groupModelFavoriteList: List<ModelGroup>) {
        txt_groups_no_item_favorite.visibility = View.GONE
        groupAdapterRv.setupFavoriteGroups(groupModelFavoriteList)
    }
}
