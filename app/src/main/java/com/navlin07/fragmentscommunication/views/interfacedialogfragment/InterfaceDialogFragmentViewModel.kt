package com.navlin07.fragmentscommunication.views.interfacedialogfragment

import com.navlin07.fragmentscommunication.base.BaseViewModel

class InterfaceDialogFragmentViewModel :
    BaseViewModel<InterfaceDialogFragmentViewState, InterfaceDialogFragmentViewTransition>() {

    override fun init() {}

    fun updateValue(userText: String) {
        if (userText.isEmpty()) {
            viewState.value?.isSendEnabled = false
            viewState.value =
                InterfaceDialogFragmentViewState.EmptyFields
        } else {
            viewState.value?.isSendEnabled = true
            viewState.value =
                InterfaceDialogFragmentViewState.FilledFields
        }
    }

}