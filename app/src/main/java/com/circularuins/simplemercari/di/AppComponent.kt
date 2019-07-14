package com.circularuins.simplemercari.di

import com.circularuins.simplemercari.MercariApplication
import dagger.Component
import javax.inject.Singleton

/**
 * アプリケーションレベルの依存性注入
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: MercariApplication)

    fun plus(mainModule: MainModule, netModule: NetModule): MainComponent

    fun plus(listModule: ListModule, netModule: NetModule): ListComponent
}