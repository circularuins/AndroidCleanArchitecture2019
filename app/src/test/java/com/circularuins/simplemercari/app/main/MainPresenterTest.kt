package com.circularuins.simplemercari.app.main

import android.content.Context
import com.circularuins.simplemercari.app.common.ApiErrorView
import com.circularuins.simplemercari.domain.model.Master
import com.circularuins.simplemercari.domain.usecase.StartUseCase
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
 * メイン画面のビューロジックのテスト
 */
class MainPresenterTest {

    private val DUMMY_STRING = "dummy"
    private val DUMMY_LIST = listOf(Master(DUMMY_STRING, DUMMY_STRING))

    private var view = mock<MainContract.View> {
        on { requestScope() } doReturn Mockito.mock(CompletableSource::class.java)
    }

    private val errorView = mock<ApiErrorView> {}

    private val context = mock<Context> {
        on { getString(any())} doReturn DUMMY_STRING
    }

    private val useCase = mock<StartUseCase> {
        on { fetchMasterData() } doReturn Single.just(DUMMY_LIST)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun start() {
        val presenter: MainContract.Presenter = MainPresenter(
            context,
            view,
            errorView,
            useCase
        )
        presenter.start()

        // 適切なビューメソッドが呼び出されているか確認
        verify(view).initToolBar()
        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).setTab(DUMMY_LIST)
    }
}