package com.circularuins.simplemercari.di

import com.circularuins.simplemercari.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}