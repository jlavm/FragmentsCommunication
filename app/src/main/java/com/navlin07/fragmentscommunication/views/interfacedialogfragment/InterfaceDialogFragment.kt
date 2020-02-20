package com.navlin07.fragmentscommunication.views.interfacedialogfragment

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import com.navlin07.fragmentscommunication.R
import com.navlin07.fragmentscommunication.base.BaseDialogFragment
import com.navlin07.fragmentscommunication.extensions.setSafeOnClickListener
import kotlinx.android.synthetic.main.dialog_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class InterfaceDialogFragment :
    BaseDialogFragment<InterfaceDialogFragmentViewState, InterfaceDialogFragmentViewTransition, InterfaceDialogFragmentViewModel>() {

    private var response: InterfaceDialogResponse? = null

    // ---- BaseDialogFragment Implementation ----

    override val viewModel: InterfaceDialogFragmentViewModel by viewModel()

    override fun getLayout(): Int = R.layout.dialog_fragment

    override fun initView() {
        tvTitleDialog?.text = getString(R.string.dialog_interface_fragment_name)
    }

    override fun manageState(state: InterfaceDialogFragmentViewState) {
        when (state) {
            is InterfaceDialogFragmentViewState.EmptyFields -> {
                tvSendOption?.isEnabled = state.isSendEnabled
            }

            is InterfaceDialogFragmentViewState.FilledFields -> {
                tvSendOption?.isEnabled = state.isSendEnabled
            }
        }
    }

    override fun manageTransition(transition: InterfaceDialogFragmentViewTransition) {}

    override fun initListeners() {
        tvSendOption.setSafeOnClickListener {
            response?.onTextAdded(tiedtDialogValue.text.toString())
            dismiss()
        }
        tvCancelOption.setSafeOnClickListener {
            dismiss()
        }
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateValue(tiedtDialogValue.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nothing to implement here.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Nothing to implement here.
            }
        }
        tiedtDialogValue?.addTextChangedListener(textWatcher)
    }

    override fun clearListeners() {}

    // ---- END BaseDialogFragment Implementation ----

    // ---- InterfaceDialogResponse Interface ----

    override fun onAttach(context: Context) {
        super.onAttach(context)
        response = if (context is InterfaceDialogResponse) {
            context
        } else {
            parentFragment as? InterfaceDialogResponse?
        }
    }

    override fun onDetach() {
        super.onDetach()
        response = null
    }

    interface InterfaceDialogResponse {
        fun onTextAdded(value: String)
    }

    // ---- END InterfaceDialogResponse Interface ----

    companion object {
        const val TAG = "InterfaceDialogFragment"
        fun newInstance() = InterfaceDialogFragment()
    }

}