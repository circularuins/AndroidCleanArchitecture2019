package com.circularuins.simplemercari.domain.repository

import com.circularuins.simplemercari.domain.model.Master
import io.reactivex.Single

interface MasterRepository {

    fun getMaster(): Single<List<Master>>
}