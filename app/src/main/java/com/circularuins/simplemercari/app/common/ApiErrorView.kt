package com.circularuins.simplemercari.app.common

/**
 * 通信処理を行う画面が実装するインターフェイス
 */
interface ApiErrorView {
    /**
     * 通信エラー表示
     */
    fun showNetworkError()

    /**
     * サーバーエラー、それ以外のエラーの表示
     *
     * @param error エラーメッセージ
     */
    fun showError(error: String)
}