package com.navlin07.fragmentscommunication.views.lambdafragment

import com.navlin07.fragmentscommunication.R
import com.navlin07.fragmentscommunication.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class LambdaFragment :
    BaseFragment<LambdaFragmentViewState, LambdaFragmentViewTransition, LambdaFragmentViewModel>(
        R.layout.fragment_lambda
    ) {

    // ---- BaseFragment Implementation ----

    override val viewModel: LambdaFragmentViewModel by viewModel()

    override fun initView() {

    }

    override fun manageState(state: LambdaFragmentViewState) {

    }

    override fun manageTransition(transition: LambdaFragmentViewTransition) {

    }

    override fun initListeners() {

    }

    override fun clearListeners() {

    }

    // ---- END BaseFragment Implementation ----

    companion object {
        const val TAG = "LambdaFragment"
        fun newInstance() = LambdaFragment()
    }
}