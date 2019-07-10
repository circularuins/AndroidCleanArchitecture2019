package com.circularuins.simplemercari.domain.usecase

import com.circularuins.simplemercari.domain.model.Master
import com.circularuins.simplemercari.domain.repository.MasterRepository
import io.reactivex.Single

class StartUseCase(
    private val repository: MasterRepository
) {

    fun fetchMasterData(): Single<List<Master>> = repository.getMaster()
}