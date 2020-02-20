package com.navlin07.fragmentscommunication.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.navlin07.fragmentscommunication.R

abstract class BaseDialogFragment<
        ViewState : Parcelable,
        ViewTransition,
        ViewModel : BaseViewModel<ViewState, ViewTransition>>(
    private val isFullScreen: Boolean = false
) : DialogFragment() {

    protected abstract val viewModel: ViewModel

    private var viewCreated = false
    private var isRecreated = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    protected abstract fun getLayout(): Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        deleteWindowBackground()
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

    private fun deleteWindowBackground() {
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onStart() {
        super.onStart()
        if (isFullScreen) {
            dialog?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (isFullScreen) {
            setStyle(STYLE_NORMAL, R.style.DialogNoTitleTheme)
        }
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        private const val VIEW_STATE = "VIEW_STATE"
    }
}