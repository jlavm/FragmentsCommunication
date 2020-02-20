package com.navlin07.fragmentscommunication.base

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

abstract class BaseActivity<ViewState : Parcelable, ViewTransition, ViewModel : BaseViewModel<ViewState, ViewTransition>>(
    @LayoutRes resLayout: Int
) : AppCompatActivity(resLayout) {

    abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            savedInstanceState.getParcelable<ViewState>(VIEW_STATE)?.let { viewState ->
                viewModel.setViewState(viewState)
            }
        }

        viewModel.config()
        initView()
        initListeners()
        initObservers()
    }

    abstract fun initView()

    private fun initObservers() {
        viewModel.getViewState().observe(this, Observer {
            if (it != null) {
                manageState(state = it)
            }
        })

        viewModel.getViewTransition().observe(this, Observer {
            if (it != null) {
                manageTransition(transition = it)
            }
        })
    }

    abstract fun manageState(state: ViewState)

    abstract fun manageTransition(transition: ViewTransition)

    abstract fun initListeners()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(VIEW_STATE, viewModel.getViewState().value)
    }

    override fun onDestroy() {
        super.onDestroy()

        clearObservers()
        clearListeners()
    }

    private fun clearObservers() {
        viewModel.getViewState().removeObservers(this)
        viewModel.getViewTransition().removeObservers(this)
    }

    abstract fun clearListeners()

    companion object {
        private const val VIEW_STATE = "VIEW_STATE"
    }
}