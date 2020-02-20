package com.navlin07.fragmentscommunication.views.lambdafragment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class LambdaFragmentViewState : Parcelable {
    @Parcelize
    object InitView : LambdaFragmentViewState()
}