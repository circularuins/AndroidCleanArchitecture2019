package com.circularuins.simplemercari.infra.repository

import com.circularuins.simplemercari.di.DaggerTestComponent
import com.circularuins.simplemercari.di.TestModule
import com.circularuins.simplemercari.domain.repository.MasterRepository
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class MasterRepositoryImplTest : BaseRestRepositoryTest() {

    @Inject
    lateinit var repository: MasterRepository

    @Before
    override fun setUp() {
        super.setUp()
        DaggerTestComponent.builder()
            .testModule(TestModule())
            .build()
            .inject(this)
    }

    @Test
    fun getMaster() {
        val response =
            MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody(masterJson)
        server.enqueue(response)

        val masters = repository.getMaster()
            .test()
            .assertComplete()
            .values()[0]
        assertEquals(masters[0].name, "All")
        assertEquals(masters[0].requestType, "all")
        assertEquals(masters[1].name, "Men")
        assertEquals(masters[1].requestType, "men")
        assertEquals(masters[2].name, "Women")
        assertEquals(masters[2].requestType, "women")
    }

    private val masterJson: String
        get() = """[
    {
      "name" : "All",
      "data" : "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/all.json"
    },
    {
      "name" : "Men",
      "data" : "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/men.json"
    },
    {
      "name" : "Women",
      "data" : "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/women.json"
    }
]"""
}