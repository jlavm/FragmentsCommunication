package com.navlin07.fragmentscommunication.extensions

import androidx.fragment.app.FragmentTransaction
import com.navlin07.fragmentscommunication.R

fun FragmentTransaction.getAnimation(animation: TransactionAnimation): FragmentTransaction =
    when (animation) {
        TransactionAnimation.PUSH_FRAGMENT -> {
            this.setCustomAnimations(
                R.anim.fragment_enter,
                R.anim.fragment_exit,
                R.anim.fragment_pop_enter,
                R.anim.fragment_pop_exit
            )
        }
        TransactionAnimation.PUSH_FRAGMENT_AND_FADE -> {
            this.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        }
        TransactionAnimation.PUSH_MODAL -> {
            this.setCustomAnimations(
                R.anim.fragment_enter_modal,
                R.anim.fragment_exit_modal,
                R.anim.fragment_pop_enter_modal,
                R.anim.fragment_pop_exit_modal
            )
        }
    }