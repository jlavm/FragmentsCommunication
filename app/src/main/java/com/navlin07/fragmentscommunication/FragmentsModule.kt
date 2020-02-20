package com.navlin07.fragmentscommunication

import android.app.Application
import com.navlin07.fragmentscommunication.views.interfacedialogfragment.InterfaceDialogFragmentViewModel
import com.navlin07.fragmentscommunication.views.interfacefragment.InterfaceFragmentViewModel
import com.navlin07.fragmentscommunication.views.lambdadialogfragment.LambdaDialogFragmentViewModel
import com.navlin07.fragmentscommunication.views.lambdafragment.LambdaFragmentViewModel
import com.navlin07.fragmentscommunication.views.main.MainViewModel
import com.navlin07.fragmentscommunication.views.selector.SelectorFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(
            listOf(
                fragmentModule
            )
        )
    }
}

val fragmentModule = module {

    viewModel {
        MainViewModel()
    }

    viewModel {
        SelectorFragmentViewModel()
    }

    viewModel {
        LambdaFragmentViewModel()
    }

    viewModel {
        InterfaceFragmentViewModel()
    }

    viewModel {
        InterfaceDialogFragmentViewModel()
    }

    viewModel {
        LambdaDialogFragmentViewModel()
    }

}

