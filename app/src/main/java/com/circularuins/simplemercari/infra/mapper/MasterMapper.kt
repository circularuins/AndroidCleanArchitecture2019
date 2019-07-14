package com.circularuins.simplemercari.infra.mapper

import com.circularuins.simplemercari.infra.data.Master
import com.circularuins.simplemercari.infra.rest.ApiClient

/**
 * Master(Data) -> Master(Model)への変換ロジック
 */
fun Master.convert(): com.circularuins.simplemercari.domain.model.Master {
    return com.circularuins.simplemercari.domain.model.Master(
        name ?: "",
        convertDataToType()
    )
}

/**
 * dataの文字列から、リクエストに使用する文字列を抽出する
 *
 * 【例】
 * 入力："https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/all.json"
 * 出力："all"
 */
fun Master.convertDataToType(): String {
    return data?.let {

        val regex = Regex(ApiClient.BASE_URL + """([\w]+)\.json""")
        val match = regex.matchEntire(it)

        match?.let { m ->
            val matches = m.groupValues
            if (matches.size <= 1) return ""
            matches[1]
        } ?: ""
    } ?: ""
}