package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.android.kotlinrealmtoothpickvk.R
import com.example.android.kotlinrealmtoothpickvk.data.repository.ModelGroup
import com.example.android.kotlinrealmtoothpickvk.presentation.adapter.GroupAdapterRv
import com.example.android.kotlinrealmtoothpickvk.presentation.adapter.Listener
import com.example.android.kotlinrealmtoothpickvk.presentation.adapter.TextListenerAdapter
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.presenter.GroupPresenter
import com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view.GroupView
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.activity_main.*
import com.vk.sdk.util.VKUtil

class MainActivity : GroupView, BaseActivity() {

 //  lateinit var floatButton: FloatingActionButton



    @InjectPresenter
    lateinit var groupPresenter: GroupPresenter

   lateinit var adapter: GroupAdapterRv

 /*   @ProvidePresenter
    fun providePresenter(): GroupPresenter {
        return groupPresenter
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
  //      adapter = GroupAdapterRv()
        setupRecyclerView()
        clickButton()
        txtListener()
        VKSdk.login(this, VKScope.GROUPS)
      /*  if (isNetworkConnected()) {
            VKSdk.login(this, VKScope.GROUPS)
        }*/
    }

    private fun setupRecyclerView() {
        adapter  = GroupAdapterRv()
var recyclerView: RecyclerView = findViewById(R.id.recycler_groups)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false) as RecyclerView.LayoutManager?
        recyclerView.setHasFixedSize(true)
        favoriteListener(adapter)
    }

    private fun txtListener() {
        txt_search.addTextChangedListener(object : TextListenerAdapter() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
         //       adapter.filter(s.toString())
            }
        })
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



    private fun favoriteListener(groupAdapterRv: GroupAdapterRv) {
        groupAdapterRv.listener = (object : Listener {
            override fun onClick(groupModel: ModelGroup, isChecked: Boolean) {
                groupPresenter.onSetFavorite(groupModel, isChecked)
            }
        })
    }
}
