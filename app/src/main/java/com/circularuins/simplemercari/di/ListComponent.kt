package com.circularuins.simplemercari.di

import com.circularuins.simplemercari.app.list.ItemListFragment
import dagger.Subcomponent

/**
 * リスト画面の依存性注入
 */
@Subcomponent(modules = [ListModule::class, NetModule::class])
interface ListComponent {
    fun inject(fragment: ItemListFragment)
}