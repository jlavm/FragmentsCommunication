package com.navlin07.fragmentscommunication.base

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<
        ViewState : Parcelable,
        ViewTransition,
        ViewModel : BaseViewModel<ViewState, ViewTransition>>(@LayoutRes resLayout: Int) :
    Fragment(resLayout) {

    abstract val viewModel: ViewModel

    private var viewCreated = false
    private var isRecreated = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            viewCreated = true
            isRecreated = true

            savedInstanceState.getParcelable<ViewState>(VIEW_STATE)?.let { viewState ->
                viewModel.setViewState(viewState)
            }
        } else {
            isRecreated = if (viewCreated) {
                true
            } else {
                viewCreated = true
                false
            }
        }

        viewModel.config()
        initView()
        initListeners()
        initObservers()
    }

    abstract fun initView()

    private fun initObservers() {
        viewModel.getViewState().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                manageState(state = it)
            }
        })

        viewModel.getViewTransition().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                manageTransition(transition = it)
            }
        })
    }

    protected abstract fun manageState(state: ViewState)

    protected abstract fun manageTransition(transition: ViewTransition)

    protected abstract fun initListeners()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(VIEW_STATE, viewModel.getViewState().value)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        clearObservers()
        clearListeners()
    }

    private fun clearObservers() {
        viewModel.getViewState().removeObservers(viewLifecycleOwner)
        viewModel.getViewTransition().removeObservers(viewLifecycleOwner)
    }

    protected abstract fun clearListeners()

    companion object {
        private const val VIEW_STATE = "VIEW_STATE"
    }
}