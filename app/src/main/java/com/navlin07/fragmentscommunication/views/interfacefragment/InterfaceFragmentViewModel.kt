package com.navlin07.fragmentscommunication.views.interfacefragment

import com.navlin07.fragmentscommunication.base.BaseViewModel

class InterfaceFragmentViewModel :
    BaseViewModel<InterfaceFragmentViewState, InterfaceFragmentViewTransition>() {

    override fun init() {
        viewState.value = InterfaceFragmentViewState.InitView
    }

    fun navigateToDialogFragment() {
        viewTransition.value = InterfaceFragmentViewTransition.NavigateToDialogFragment
    }

}