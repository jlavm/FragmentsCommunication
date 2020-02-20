package com.navlin07.fragmentscommunication.views.lambdadialogfragment

import com.navlin07.fragmentscommunication.base.BaseViewModel

class LambdaDialogFragmentViewModel :
    BaseViewModel<LambdaDialogFragmentViewState, LambdaDialogFragmentViewTransition>() {

    override fun init() {}

    fun updateValue(userText: String) {
        if (userText.isEmpty()) {
            viewState.value?.isSendEnabled = false
            viewState.value =
                LambdaDialogFragmentViewState.EmptyFields
        } else {
            viewState.value?.isSendEnabled = true
            viewState.value =
                LambdaDialogFragmentViewState.FilledFields
        }
    }

}