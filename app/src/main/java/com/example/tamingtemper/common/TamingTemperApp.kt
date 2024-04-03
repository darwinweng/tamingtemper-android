package com.example.tamingtemper.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TamingTemperApp: Application() {

    companion object {
        val TAG: String = "TamingTemperApp"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()
        context = this
    }
}