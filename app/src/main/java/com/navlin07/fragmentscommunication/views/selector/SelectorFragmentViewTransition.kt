package com.navlin07.fragmentscommunication.views.selector

sealed class SelectorFragmentViewTransition {
    object NavigateToInterfaceFragment : SelectorFragmentViewTransition()
    object NavigateToLambdaFragment : SelectorFragmentViewTransition()
    object NavigateToDialogInterfaceFragment : SelectorFragmentViewTransition()
    object NavigateToDialogLambdaFragment : SelectorFragmentViewTransition()
}