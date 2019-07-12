package com.circularuins.simplemercari.domain.usecase

import com.circularuins.simplemercari.domain.model.Item
import com.circularuins.simplemercari.domain.repository.ItemRepository
import io.reactivex.Single

class ListUseCase(
    private val repository: ItemRepository
) {

    fun fetchListData(requestType: String): Single<List<Item>> = repository.getItems(requestType)
}