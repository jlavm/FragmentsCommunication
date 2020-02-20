package com.navlin07.fragmentscommunication.views.selector

import com.navlin07.fragmentscommunication.base.BaseViewModel

class SelectorFragmentViewModel :
    BaseViewModel<SelectorFragmentViewState, SelectorFragmentViewTransition>() {

    override fun init() {
        viewState.value = SelectorFragmentViewState.InitView
    }

    fun navigateToInterfaceFragment() {
        viewTransition.value = SelectorFragmentViewTransition.NavigateToInterfaceFragment
    }

    fun navigateToLambdaFragment() {
        viewTransition.value = SelectorFragmentViewTransition.NavigateToLambdaFragment
    }

    fun navigateToDialogInterfaceFragment() {
        viewTransition.value = SelectorFragmentViewTransition.NavigateToDialogInterfaceFragment
    }

    fun navigateToDialogLambdaFragment() {
        viewTransition.value = SelectorFragmentViewTransition.NavigateToDialogLambdaFragment
    }

}