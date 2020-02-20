package com.navlin07.fragmentscommunication.views.interfacefragment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class InterfaceFragmentViewState : Parcelable {
    @Parcelize
    object InitView : InterfaceFragmentViewState()
}