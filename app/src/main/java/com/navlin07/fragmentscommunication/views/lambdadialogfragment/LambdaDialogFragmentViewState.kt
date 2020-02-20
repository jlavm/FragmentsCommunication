package com.navlin07.fragmentscommunication.views.lambdadialogfragment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class LambdaDialogFragmentViewState(
    var isSendEnabled: Boolean
) : Parcelable {
    @Parcelize
    object EmptyFields : LambdaDialogFragmentViewState(false)

    @Parcelize
    object FilledFields : LambdaDialogFragmentViewState(true)
}