package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.android.kotlinrealmtoothpickvk.R
import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.di.module.GroupModule
import com.example.android.kotlinrealmtoothpickvk.presentation.adapter.GroupAdapterRv
import com.example.android.kotlinrealmtoothpickvk.presentation.adapter.Listener
import com.example.android.kotlinrealmtoothpickvk.presentation.adapter.onTextChange
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.presenter.GroupPresenter
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view.GroupView
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.activity_main.*
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : GroupView, BaseActivity() {
    var vkLoad: Boolean = true
    lateinit var adapter: GroupAdapterRv

    @Inject
    @InjectPresenter
    lateinit var groupPresenter: GroupPresenter

    @ProvidePresenter
    fun providePresenter(): GroupPresenter {
        return groupPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.let { vkLoad = savedInstanceState.getBoolean("vkLoad") }
        val scope: Scope = Toothpick.openScope("mainScope")
        scope.installModules(GroupModule())
        Toothpick.inject(this, scope)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        clickButton()
        txtListener()
        if (isNetworkConnected() && vkLoad) {
            VKSdk.login(this, VKScope.GROUPS)
            vkLoad = false
        }
    }

    private fun setupRecyclerView() {
        adapter = GroupAdapterRv()
        recyclerGroups.adapter = adapter
        recyclerGroups.layoutManager =
            LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        recyclerGroups.setHasFixedSize(true)
        favoriteListener(adapter)
    }

    private fun txtListener() {
        txtSearch.onTextChange { text, _, _, _ ->
            adapter.filter(text.toString())
        }
    }

    private fun clickButton() {
        floatButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        groupPresenter.loginVk(requestCode, resultCode, data)
    }

    override fun startLoading() {
        txtGroupsNoItem.visibility = View.GONE
        recyclerGroups.visibility = View.GONE
        cpvGroups.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpvGroups.visibility = View.GONE
    }

    override fun showError(textResource: Int) {
        txtGroupsNoItem.text = getString(textResource)
    }

    override fun setupEmptyList() {
        txtGroupsNoItem.visibility = View.VISIBLE
        recyclerGroups.visibility = View.GONE
    }

    override fun setupGroupsList(groupsList: List<ModelGroup>) {
        txtGroupsNoItem.visibility = View.GONE
        recyclerGroups.visibility = View.VISIBLE
        adapter.setupGroups(groupModelList = groupsList)
    }

    override fun onResume() {
        super.onResume()
        groupPresenter.onInitGroupsDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope("mainScope")
    }

    private fun favoriteListener(groupAdapterRv: GroupAdapterRv) {
        groupAdapterRv.listener = (object : Listener {
            override fun onClick(groupModel: ModelGroup, isChecked: Boolean) {
                groupPresenter.onSetFavorite(groupModel, isChecked)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState!!.putBoolean("vkLoad", vkLoad)
    }
}
