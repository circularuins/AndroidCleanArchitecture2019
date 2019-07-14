package com.circularuins.simplemercari.di

import com.circularuins.simplemercari.infra.repository.BaseRestRepositoryTest
import com.circularuins.simplemercari.infra.repository.ItemRepositoryImplTest
import com.circularuins.simplemercari.infra.repository.MasterRepositoryImplTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule::class])
interface TestComponent {
    fun inject(repositoryImplTest: MasterRepositoryImplTest)
    fun inject(repositoryImplTest: ItemRepositoryImplTest)
    fun inject(repositoryImplTest: BaseRestRepositoryTest)
}