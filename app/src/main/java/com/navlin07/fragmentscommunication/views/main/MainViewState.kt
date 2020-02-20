package com.navlin07.fragmentscommunication.views.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class MainViewState : Parcelable {
    @Parcelize
    object InitView : MainViewState()
}