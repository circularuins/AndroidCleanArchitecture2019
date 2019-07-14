package com.circularuins.simplemercari.domain.usecase

import com.circularuins.simplemercari.domain.model.Item
import com.circularuins.simplemercari.domain.repository.ItemRepository
import io.reactivex.Single

/**
 * リストのユースケース
 */
class ListUseCase(
    private val repository: ItemRepository
) {

    /**
     * リストに表示するデータを取得する
     *
     * @return 商品のリスト
     */
    fun fetchListData(requestType: String): Single<List<Item>> = repository.getItems(requestType)
}