package com.navlin07.fragmentscommunication.views.interfacefragment

import com.navlin07.fragmentscommunication.R
import com.navlin07.fragmentscommunication.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class InterfaceFragment :
    BaseFragment<InterfaceFragmentViewState, InterfaceFragmentViewTransition, InterfaceFragmentViewModel>(
        R.layout.fragment_interface
    ) {

    // ---- BaseFragment Implementation ----

    override val viewModel: InterfaceFragmentViewModel by viewModel()

    override fun initView() {

    }

    override fun manageState(state: InterfaceFragmentViewState) {

    }

    override fun manageTransition(transition: InterfaceFragmentViewTransition) {

    }

    override fun initListeners() {

    }

    override fun clearListeners() {

    }

    // ---- END BaseFragment Implementation ----

    companion object {
        const val TAG = "CoursesFragment"
        fun newInstance() = InterfaceFragment()
    }
}