package com.navlin07.fragmentscommunication.views.interfacedialogfragment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class InterfaceDialogFragmentViewState(
    var isSendEnabled: Boolean
) : Parcelable {
    @Parcelize
    object EmptyFields : InterfaceDialogFragmentViewState(false)

    @Parcelize
    object FilledFields : InterfaceDialogFragmentViewState(true)
}