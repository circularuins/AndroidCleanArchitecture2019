package com.circularuins.simplemercari.domain.usecase

import com.circularuins.simplemercari.domain.model.Master
import com.circularuins.simplemercari.domain.repository.MasterRepository
import io.reactivex.Single

/**
 * 起動処理のユースケース
 */
class StartUseCase(
    private val repository: MasterRepository
) {

    /**
     * マスターデータを取得する
     *
     * @return マスターのリスト
     */
    fun fetchMasterData(): Single<List<Master>> = repository.getMaster()
}