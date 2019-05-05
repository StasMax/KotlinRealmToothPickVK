package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.ui

import android.content.Context
import android.net.ConnectivityManager
import com.arellomobile.mvp.MvpAppCompatActivity

open class BaseActivity : MvpAppCompatActivity() {

    fun isNetworkConnected(): Boolean {
        val cm: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
        return cm.activeNetworkInfo != null
    }
}