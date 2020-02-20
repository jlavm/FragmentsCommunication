package com.navlin07.fragmentscommunication.views.lambdadialogfragment

import android.text.Editable
import android.text.TextWatcher
import com.navlin07.fragmentscommunication.R
import com.navlin07.fragmentscommunication.base.BaseDialogFragment
import com.navlin07.fragmentscommunication.extensions.setSafeOnClickListener
import kotlinx.android.synthetic.main.dialog_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class LambdaDialogFragment :
    BaseDialogFragment<LambdaDialogFragmentViewState, LambdaDialogFragmentViewTransition, LambdaDialogFragmentViewModel>() {

    lateinit var onTextAdded: (String) -> Unit

    // ---- BaseDialogFragment Implementation ----

    override val viewModel: LambdaDialogFragmentViewModel by viewModel()

    override fun getLayout(): Int = R.layout.dialog_fragment

    override fun initView() {
        tvTitleDialog?.text = getString(R.string.dialog_lambda_fragment_name)
    }

    override fun manageState(state: LambdaDialogFragmentViewState) {
        when (state) {
            is LambdaDialogFragmentViewState.EmptyFields -> {
                tvSendOption?.isEnabled = state.isSendEnabled
            }

            is LambdaDialogFragmentViewState.FilledFields -> {
                tvSendOption?.isEnabled = state.isSendEnabled
            }
        }
    }

    override fun manageTransition(transition: LambdaDialogFragmentViewTransition) {}

    override fun initListeners() {
        tvSendOption.setSafeOnClickListener {
            onTextAdded(tiedtDialogValue.text.toString())
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

    companion object {
        const val TAG = "LambdaDialogFragment"
        fun newInstance() = LambdaDialogFragment()
    }

}