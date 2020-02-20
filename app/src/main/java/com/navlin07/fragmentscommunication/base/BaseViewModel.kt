package com.navlin07.fragmentscommunication.base

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<State : Parcelable, Transition> : ViewModel() {

    protected val viewState: MutableLiveData<State> = MutableLiveData()
    protected val viewTransition: SingleLiveEvent<Transition> = SingleLiveEvent()

    fun getViewState(): LiveData<State> = viewState
    fun getViewTransition(): LiveData<Transition> = viewTransition

    fun setViewTransition(transition: Transition) {
        viewTransition.value = transition
    }

    fun setViewState(state: State) {
        viewState.value = state
    }

    fun config() {
        init()
    }

    abstract fun init()

}