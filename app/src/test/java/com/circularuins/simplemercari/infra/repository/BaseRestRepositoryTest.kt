package com.circularuins.simplemercari.infra.repository

import com.circularuins.simplemercari.di.DaggerTestComponent
import com.circularuins.simplemercari.di.TestComponent
import com.circularuins.simplemercari.di.TestModule
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import javax.inject.Inject

abstract class BaseRestRepositoryTest {

    @Inject
    lateinit var server: MockWebServer
    @Inject
    lateinit var schedulerProvider: TestSchedulerProvider

    lateinit var component: TestComponent

    @Before
    @Throws(Exception::class)
    open fun setUp() {
        component = DaggerTestComponent.builder()
            .testModule(TestModule())
            .build()
        component.inject(this)

        RxJavaPlugins.setComputationSchedulerHandler { s -> Schedulers.trampoline() }

        schedulerProvider = TestSchedulerProvider()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
        RxJavaPlugins.reset()
    }
}