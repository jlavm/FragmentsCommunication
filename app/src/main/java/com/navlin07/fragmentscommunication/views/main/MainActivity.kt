package com.navlin07.fragmentscommunication.views.main

import androidx.fragment.app.Fragment
import com.navlin07.fragmentscommunication.R
import com.navlin07.fragmentscommunication.base.BaseActivity
import com.navlin07.fragmentscommunication.extensions.TransactionAnimation
import com.navlin07.fragmentscommunication.extensions.replaceFragment
import com.navlin07.fragmentscommunication.extensions.showShortToast
import com.navlin07.fragmentscommunication.views.interfacedialogfragment.InterfaceDialogFragment
import com.navlin07.fragmentscommunication.views.interfacefragment.InterfaceFragment
import com.navlin07.fragmentscommunication.views.lambdadialogfragment.LambdaDialogFragment
import com.navlin07.fragmentscommunication.views.lambdafragment.LambdaFragment
import com.navlin07.fragmentscommunication.views.selector.SelectorFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity :
    BaseActivity<MainViewState, MainViewTransition, MainViewModel>
        (R.layout.activity_main),
    SelectorFragment.InterfaceResponse,
    InterfaceDialogFragment.InterfaceDialogResponse {

    // ---- BaseActivity Implementation ----

    override val viewModel: MainViewModel by viewModel()

    override fun initView() {}

    override fun manageState(state: MainViewState) {}

    override fun manageTransition(transition: MainViewTransition) {
        when (transition) {
            is MainViewTransition.NavigateToSelectorFragment -> {
                navigateToSelectorFragment()
            }
            is MainViewTransition.NavigateToInterfaceFragment -> {
                navigateToInterfaceFragment()
            }
            is MainViewTransition.NavigateToLambdaFragment -> {
                navigateToLambdaFragment()
            }
            is MainViewTransition.NavigateToDialogInterfaceFragment -> {
                navigateToInterfaceDialog()
            }
            is MainViewTransition.NavigateToDialogLambdaFragment -> {
                navigateToLambdaDialog()
            }
            is MainViewTransition.ShowToastValue -> {
                showSmallToast(transition.value)
            }
        }
    }

    override fun initListeners() {

    }

    override fun clearListeners() {

    }

    // ---- END BaseActivity Implementation ----

    override fun onAttachFragment(fragment: Fragment) {
        when (fragment) {
            is SelectorFragment -> {
                fragment.run {
                    navigateToLambdaFragment = {
                        this@MainActivity.viewModel.navigateToLambdaFragment()
                    }
                    navigateToDialogLambdaFragment = {
                        this@MainActivity.viewModel.navigateToDialogLambdaFragment()
                    }
                }
            }
            is LambdaDialogFragment -> {
                fragment.run {
                    onTextAdded = { value ->
                        this@MainActivity.viewModel.showShortToastValue(value)
                    }
                }
            }
        }
        super.onAttachFragment(fragment)
    }

    private fun navigateToSelectorFragment() {
        replaceFragment(
            SelectorFragment.newInstance(),
            R.id.flFragmentsContainer,
            SelectorFragment.TAG,
            false,
            null
        )
    }

    private fun navigateToInterfaceFragment() {
        replaceFragment(
            InterfaceFragment.newInstance(),
            R.id.flFragmentsContainer,
            InterfaceFragment.TAG,
            addToStack = true,
            animation = TransactionAnimation.PUSH_FRAGMENT
        )
    }

    private fun navigateToLambdaFragment() {
        replaceFragment(
            LambdaFragment.newInstance(),
            R.id.flFragmentsContainer,
            InterfaceFragment.TAG,
            addToStack = true,
            animation = TransactionAnimation.PUSH_FRAGMENT
        )
    }

    private fun navigateToLambdaDialog() {
        val lambdaDialogFragment = LambdaDialogFragment.newInstance()
        lambdaDialogFragment.show(supportFragmentManager, LambdaDialogFragment.TAG)
    }

    private fun navigateToInterfaceDialog() {
        val interfaceDialogFragment = InterfaceDialogFragment.newInstance()
        interfaceDialogFragment.show(supportFragmentManager, InterfaceDialogFragment.TAG)
    }

    private fun showSmallToast(value: String) {
        showShortToast(value)
    }

    // ---- InterfaceResponse Interface ----

    override fun interfaceFragmentView() {
        viewModel.navigateToInterfaceFragment()
    }

    override fun interfaceDialogFragmentView() {
        viewModel.navigateToDialogInterfaceFragment()
    }

    // ---- END InterfaceResponse Interface ----

    // ---- InterfaceDialogResponse Interface ----

    override fun onTextAdded(value: String) {
        viewModel.showShortToastValue(value)
    }

    // ---- END InterfaceDialogResponse Interface ----

}
