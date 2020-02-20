package com.navlin07.fragmentscommunication.views.selector

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class SelectorFragmentViewState : Parcelable {
    @Parcelize
    object InitView : SelectorFragmentViewState()
}