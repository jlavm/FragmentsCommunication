package com.navlin07.fragmentscommunication.views.lambdafragment

import com.navlin07.fragmentscommunication.base.BaseViewModel

class LambdaFragmentViewModel :
    BaseViewModel<LambdaFragmentViewState, LambdaFragmentViewTransition>() {

    override fun init() {
        viewState.value = LambdaFragmentViewState.InitView
    }

    fun navigateToDialogFragment() {
        viewTransition.value = LambdaFragmentViewTransition.NavigateToDialogFragment
    }

}