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
import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
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
        recycler_groups.adapter = adapter
        recycler_groups.layoutManager =
            LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        recycler_groups.setHasFixedSize(true)
        favoriteListener(adapter)
    }

    private fun txtListener() {
        txt_search.onTextChange { text, _, _, _ ->
            adapter.filter(text.toString())
        }
    }

    private fun clickButton() {
        float_button.setOnClickListener {
            startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        groupPresenter.loginVk(requestCode, resultCode, data)
    }

    override fun startLoading() {
        txt_groups_no_item.visibility = View.GONE
        recycler_groups.visibility = View.GONE
        cpv_groups.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpv_groups.visibility = View.GONE
    }

    override fun showError(textResource: Int) {
        txt_groups_no_item.text = getString(textResource)
    }

    override fun setupEmptyList() {
        txt_groups_no_item.visibility = View.VISIBLE
        recycler_groups.visibility = View.GONE
    }

    override fun setupGroupsList(groupsList: List<ModelGroup>) {
        txt_groups_no_item.visibility = View.GONE
        recycler_groups.visibility = View.VISIBLE
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
