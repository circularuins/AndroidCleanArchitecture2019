package com.circularuins.simplemercari.infra.repository

import com.circularuins.simplemercari.di.DaggerTestComponent
import com.circularuins.simplemercari.di.TestModule
import com.circularuins.simplemercari.domain.model.Item
import com.circularuins.simplemercari.domain.repository.ItemRepository
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/**
 * 商品データ取得Repositoryのテスト
 */
class ItemRepositoryImplTest : BaseRestRepositoryTest() {

    @Inject
    lateinit var repository: ItemRepository

    @Before
    override fun setUp() {
        super.setUp()
        DaggerTestComponent.builder()
            .testModule(TestModule())
            .build()
            .inject(this)
    }

    @Test
    fun getItems_all() {
        val response =
            MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody(allJson)
        server.enqueue(response)

        val items = repository.getItems("all")
            .test()
            .assertComplete()
            .values()[0]

        // jsonを適切にパースし、モデルに変換できているか
        assertEquals(items[0].id, "mmen1")
        assertEquals(items[0].name, "men1")
        assertEquals(items[0].status, Item.Status.OnSale)
        assertEquals(items[0].numLikes, 91)
        assertEquals(items[0].numComments, 59)
        assertEquals(items[0].price, 51)
        assertEquals(items[0].photo, "https://dummyimage.com/400x400/000/fff?text=men1")

        // 正しいリクエストが発行できているか
        val request = server.takeRequest()
        assertEquals("GET", request.method)
        assertEquals("/all.json", request.path)
    }

    @Test
    fun getItems_men() {
        val response =
            MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody(menJson)
        server.enqueue(response)

        val items = repository.getItems("men")
            .test()
            .assertComplete()
            .values()[0]

        // jsonを適切にパースし、モデルに変換できているか
        assertEquals(items[1].id, "mmen2")
        assertEquals(items[1].name, "men2")
        assertEquals(items[1].status, Item.Status.OnSale)
        assertEquals(items[1].numLikes, 81)
        assertEquals(items[1].numComments, 89)
        assertEquals(items[1].price, 2)
        assertEquals(items[1].photo, "https://dummyimage.com/400x400/000/fff?text=men2")

        // 正しいリクエストが発行できているか
        val request = server.takeRequest()
        assertEquals("GET", request.method)
        assertEquals("/men.json", request.path)
    }

    @Test
    fun getItems_women() {
        val response =
            MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody(womenJson)
        server.enqueue(response)

        val items = repository.getItems("women")
            .test()
            .assertComplete()
            .values()[0]

        // jsonを適切にパースし、モデルに変換できているか
        assertEquals(items[2].id, "mwomen3")
        assertEquals(items[2].name, "women3")
        assertEquals(items[2].status, Item.Status.SoldOut)
        assertEquals(items[2].numLikes, 45)
        assertEquals(items[2].numComments, 97)
        assertEquals(items[2].price, 27)
        assertEquals(items[2].photo, "https://dummyimage.com/400x400/000/fff?text=women3")

        // 正しいリクエストが発行できているか
        val request = server.takeRequest()
        assertEquals("GET", request.method)
        assertEquals("/women.json", request.path)
    }

    private val allJson: String
        get() = """[
    {
    "id": "mmen1",
    "name": "men1",
    "status": "on_sale",
    "num_likes": 91,
    "num_comments": 59,
    "price": 51,
    "photo": "https://dummyimage.com/400x400/000/fff?text=men1"
  },
  {
    "id": "mmen2",
    "name": "men2",
    "status": "on_sale",
    "num_likes": 81,
    "num_comments": 89,
    "price": 2,
    "photo": "https://dummyimage.com/400x400/000/fff?text=men2"
  },
  {
    "id": "mmen3",
    "name": "men3",
    "status": "sold_out",
    "num_likes": 17,
    "num_comments": 58,
    "price": 38,
    "photo": "https://dummyimage.com/400x400/000/fff?text=men3"
  }
]"""

    private val menJson: String
        get() = """[
    {
    "id": "mmen1",
    "name": "men1",
    "status": "on_sale",
    "num_likes": 91,
    "num_comments": 59,
    "price": 51,
    "photo": "https://dummyimage.com/400x400/000/fff?text=men1"
  },
  {
    "id": "mmen2",
    "name": "men2",
    "status": "on_sale",
    "num_likes": 81,
    "num_comments": 89,
    "price": 2,
    "photo": "https://dummyimage.com/400x400/000/fff?text=men2"
  },
  {
    "id": "mmen3",
    "name": "men3",
    "status": "sold_out",
    "num_likes": 17,
    "num_comments": 58,
    "price": 38,
    "photo": "https://dummyimage.com/400x400/000/fff?text=men3"
  }
]"""

    private val womenJson: String
        get() = """[
    {
    "id": "mwomen1",
    "name": "women1",
    "status": "on_sale",
    "num_likes": 33,
    "num_comments": 41,
    "price": 61,
    "photo": "https://dummyimage.com/400x400/000/fff?text=women1"
  },
  {
    "id": "mwomen2",
    "name": "women2",
    "status": "on_sale",
    "num_likes": 97,
    "num_comments": 8,
    "price": 82,
    "photo": "https://dummyimage.com/400x400/000/fff?text=women2"
  },
  {
    "id": "mwomen3",
    "name": "women3",
    "status": "sold_out",
    "num_likes": 45,
    "num_comments": 97,
    "price": 27,
    "photo": "https://dummyimage.com/400x400/000/fff?text=women3"
  }
]"""
}