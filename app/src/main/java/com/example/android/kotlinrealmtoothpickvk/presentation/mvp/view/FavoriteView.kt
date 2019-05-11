package com.example.android.kotlinrealmtoothpickvk.presentation.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.android.kotlinrealmtoothpickvk.data.model.ModelGroup

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface FavoriteView : MvpView {
    fun setupEmptyList()

    fun setupGroupsList(groupModelFavoriteList: List<ModelGroup>)
}