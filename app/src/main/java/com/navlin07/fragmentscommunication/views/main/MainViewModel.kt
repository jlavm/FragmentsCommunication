package com.navlin07.fragmentscommunication.views.main

import com.navlin07.fragmentscommunication.base.BaseViewModel

class MainViewModel : BaseViewModel<MainViewState, MainViewTransition>() {

    override fun init() {
        viewState.value = MainViewState.InitView
        viewTransition.value = MainViewTransition.NavigateToSelectorFragment
    }

    fun navigateToInterfaceFragment() {
        viewTransition.value = MainViewTransition.NavigateToInterfaceFragment
    }

    fun navigateToLambdaFragment() {
        viewTransition.value = MainViewTransition.NavigateToLambdaFragment
    }

    fun navigateToDialogLambdaFragment() {
        viewTransition.value = MainViewTransition.NavigateToDialogLambdaFragment
    }

    fun navigateToDialogInterfaceFragment() {
        viewTransition.value = MainViewTransition.NavigateToDialogInterfaceFragment
    }

    fun showShortToastValue(value: String) {
        viewTransition.value = MainViewTransition.ShowToastValue(value)
    }

}