package com.circularuins.simplemercari.di

import com.circularuins.simplemercari.app.main.MainActivity
import dagger.Subcomponent

/**
 * メイン画面への依存性注入
 */
@Subcomponent(modules = [MainModule::class, NetModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}