package com.circularuins.simplemercari

import android.app.Application
import com.circularuins.simplemercari.di.AppComponent
import com.circularuins.simplemercari.di.DaggerAppComponent

class MercariApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }

    override fun onCreate() {
        super.onCreate()
    }
}