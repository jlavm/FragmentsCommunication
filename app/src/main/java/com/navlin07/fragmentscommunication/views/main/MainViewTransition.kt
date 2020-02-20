package com.navlin07.fragmentscommunication.views.main

sealed class MainViewTransition {
    object NavigateToSelectorFragment : MainViewTransition()
    object NavigateToInterfaceFragment : MainViewTransition()
    object NavigateToLambdaFragment : MainViewTransition()
    object NavigateToDialogLambdaFragment : MainViewTransition()
    object NavigateToDialogInterfaceFragment : MainViewTransition()
    class ShowToastValue(val value: String) : MainViewTransition()
}