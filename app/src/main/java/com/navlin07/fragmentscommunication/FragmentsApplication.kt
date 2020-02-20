package com.navlin07.fragmentscommunication

import android.app.Application

class FragmentsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}