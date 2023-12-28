package com.example.sunnytask.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationGlobal:Application() {
    companion object{
       var accessToken = ""
    }
    override fun onCreate() {
        super.onCreate()
    }
}