package com.circularuins.simplemercari.app.list

import android.content.Context
import com.circularuins.simplemercari.app.ApiErrorView
import com.circularuins.simplemercari.app.mapper.convert
import com.circularuins.simplemercari.domain.usecase.ListUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.CompletableSource
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * リスト画面のビューロジックのテスト
 */
class ListPresenterTest {

    private val DUMMY_STRING = "dummy"
    private val item = com.circularuins.simplemercari.domain.model.Item(
        DUMMY_STRING, DUMMY_STRING, com.circularuins.simplemercari.domain.model.Item.Status.OnSale,
        0, 0, 0, DUMMY_STRING
    )
    private val DUMMY_LIST_MODEL = listOf(item)
    private val DUMMY_LIST_VIEWDATA = listOf(item.convert())

    private var view = mock<ListContract.View> {
        on { requestScope() } doReturn Mockito.mock(CompletableSource::class.java)
    }

    private val errorView = mock<ApiErrorView> {}

    private val context = mock<Context> {
        on { getString(any())} doReturn DUMMY_STRING
    }

    private val useCase = mock<ListUseCase> {
        on { fetchListData(DUMMY_STRING) } doReturn Single.just(DUMMY_LIST_MODEL)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun start() {
        val presenter = ListPresenter(
            context, view, errorView, useCase
        )
        presenter.start(DUMMY_STRING)

        // 適切なビューメソッドが呼び出されているか確認
        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).setList(DUMMY_LIST_VIEWDATA)
    }
}