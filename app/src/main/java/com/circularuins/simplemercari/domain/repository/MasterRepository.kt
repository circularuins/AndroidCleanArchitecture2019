package com.circularuins.simplemercari.domain.repository

import com.circularuins.simplemercari.domain.model.Master
import io.reactivex.Single

/**
 * マスターレポジトリ
 */
interface MasterRepository {

    /**
     * マスターデータを取得する
     *
     * @return マスターのリスト
     */
    fun getMaster(): Single<List<Master>>
}