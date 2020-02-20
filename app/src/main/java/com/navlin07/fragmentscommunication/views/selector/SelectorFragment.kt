package com.navlin07.fragmentscommunication.views.selector

import android.content.Context
import com.navlin07.fragmentscommunication.R
import com.navlin07.fragmentscommunication.base.BaseFragment
import com.navlin07.fragmentscommunication.extensions.setSafeOnClickListener
import kotlinx.android.synthetic.main.fragment_selector.*
import org.koin.android.viewmodel.ext.android.viewModel

class SelectorFragment :
    BaseFragment<SelectorFragmentViewState, SelectorFragmentViewTransition, SelectorFragmentViewModel>(
        R.layout.fragment_selector
    ) {

    private var response: InterfaceResponse? = null
    lateinit var navigateToLambdaFragment: () -> Unit
    lateinit var navigateToDialogLambdaFragment: () -> Unit

    // ---- BaseFragment Implementation ----

    override val viewModel: SelectorFragmentViewModel by viewModel()

    override fun initView() {}

    override fun manageState(state: SelectorFragmentViewState) {}

    override fun manageTransition(transition: SelectorFragmentViewTransition) {
        when (transition) {
            is SelectorFragmentViewTransition.NavigateToInterfaceFragment -> {
                response?.interfaceFragmentView()
            }
            is SelectorFragmentViewTransition.NavigateToDialogInterfaceFragment -> {
                response?.interfaceDialogFragmentView()
            }
            is SelectorFragmentViewTransition.NavigateToLambdaFragment -> {
                navigateToLambdaFragment()
            }
            is SelectorFragmentViewTransition.NavigateToDialogLambdaFragment -> {
                navigateToDialogLambdaFragment()
            }
        }
    }

    override fun initListeners() {
        mcvSelectorOptionOneTitle.setSafeOnClickListener {
            viewModel.navigateToInterfaceFragment()
        }
        mcvSelectorOptionTwoTitle.setSafeOnClickListener {
            viewModel.navigateToLambdaFragment()
        }
        mcvSelectorOptionThreeTitle.setSafeOnClickListener {
            viewModel.navigateToDialogInterfaceFragment()
        }
        mcvSelectorOptionFourTitle.setSafeOnClickListener {
            viewModel.navigateToDialogLambdaFragment()
        }
    }

    override fun clearListeners() {

    }

    // ---- END BaseFragment Implementation ----

    // ---- InterfaceResponse Interface ----

    override fun onAttach(context: Context) {
        super.onAttach(context)
        response = if (context is InterfaceResponse) {
            context
        } else {
            parentFragment as? InterfaceResponse?
        }
    }

    override fun onDetach() {
        super.onDetach()
        response = null
    }

    interface InterfaceResponse {
        fun interfaceFragmentView()
        fun interfaceDialogFragmentView()
    }

    // ---- END InterfaceResponse Interface ----


    companion object {
        const val TAG = "SelectorFragment"
        fun newInstance() = SelectorFragment()
    }
}