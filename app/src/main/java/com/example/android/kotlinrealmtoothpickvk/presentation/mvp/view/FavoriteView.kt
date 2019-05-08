package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FavoriteView : MvpView {
    fun setupEmptyList()

    fun setupGroupsList(groupModelFavoriteList: List<ModelGroup>)
}