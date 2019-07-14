package com.circularuins.simplemercari.domain.repository

import com.circularuins.simplemercari.domain.model.Item
import io.reactivex.Single

/**
 * 商品レポジトリ
 */
interface ItemRepository {

    /**
     * 商品一覧データを取得する
     *
     * @param type 商品の種類（例："men"）
     * @return 商品のリスト
     */
    fun getItems(type: String): Single<List<Item>>
}